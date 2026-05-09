package com.proyectohospital.main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.proyectohospital.controllers.AccesoController;
import com.proyectohospital.controllers.EstudianteController;
import com.proyectohospital.dao.impl.*;
import com.proyectohospital.dao.interfaces.*;
import com.proyectohospital.entities.*;
import com.proyectohospital.services.*;
import com.proyectohospital.utils.BusinessException;
import com.proyectohospital.utils.JPAUtil;

public class Main {
    public static void main(String[] args) {
        UniversidadDAO universidadDAO = new UniversidadDAOImpl();
        EspecialidadDAO especialidadDAO = new EspecialidadDAOImpl();
        TutorDAO tutorDAO = new TutorDAOImpl();
        DocenteDAO docenteDAO = new DocenteDAOImpl();
        ServicioDAO servicioDAO = new ServicioDAOImpl();
        EstudianteDAO estudianteDAO = new EstudianteDAOImpl();
        ProgramacionDAO programacionDAO = new ProgramacionDAOImpl();
        ProgramacionDetalleDAO detalleDAO = new ProgramacionDetalleDAOImpl();
        CapacidadPracticaDAO capacidadDAO = new CapacidadPracticaDAOImpl();
        AccesoDAOImpl accesoDAO = new AccesoDAOImpl();
        TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAOImpl();
        DocumentoEstudianteDAO documentoDAO = new DocumentoEstudianteDAOImpl();
        VacunaDAO vacunaDAO = new VacunaDAOImpl();
        EstudianteVacunaDAO estudianteVacunaDAO = new EstudianteVacunaDAOImpl();

        EstudianteService estudianteService = new EstudianteService(estudianteDAO);
        DocumentoService documentoService = new DocumentoService(tipoDocumentoDAO, documentoDAO);
        VacunaService vacunaService = new VacunaService(vacunaDAO, estudianteVacunaDAO, estudianteDAO);
        ProgramacionService programacionService = new ProgramacionService(programacionDAO, detalleDAO, capacidadDAO);
        AccesoService accesoService = new AccesoService(accesoDAO, estudianteDAO, programacionService, documentoService, vacunaService);

        EstudianteController estudianteController = new EstudianteController(estudianteService);
        AccesoController accesoController = new AccesoController(accesoService);

        try {
            String sufijo = String.valueOf(System.currentTimeMillis());
            LocalDate fechaPractica = LocalDate.now().plusDays(1);
            LocalTime horaInicio = LocalTime.of(7, 0);
            LocalTime horaFin = LocalTime.of(9, 0);
            LocalDateTime horaIngreso = LocalDateTime.of(fechaPractica, LocalTime.of(8, 0));

            System.out.println("=== DEMO COMPLETA SISTEMA DE PRACTICAS HOSPITALARIAS ===");

            Universidad universidad = new Universidad("Universidad Pedagogica Demo " + sufijo, "Tunja");
            universidadDAO.save(universidad);
            System.out.println("1. Tabla universidad: registro creado");

            Especialidad urgencias = new Especialidad("Urgencias " + sufijo);
            Especialidad pediatria = new Especialidad("Pediatria " + sufijo);
            especialidadDAO.save(urgencias);
            especialidadDAO.save(pediatria);
            System.out.println("2. Tabla especialidad: registros creados");

            CapacidadPractica cupoUrgencias = new CapacidadPractica(urgencias, horaInicio, horaFin, 25);
            CapacidadPractica cupoPediatria = new CapacidadPractica(pediatria, LocalTime.of(9, 0), LocalTime.of(11, 0), 12);
            capacidadDAO.save(cupoUrgencias);
            capacidadDAO.save(cupoPediatria);
            System.out.println("3. Tabla capacidad_practica: Urgencias 07:00-09:00 con 25 cupos");

            Tutor tutorUniversidad = new Tutor("Dra. Marcela Torres " + sufijo, TipoTutor.UNIVERSIDAD, universidad);
            Tutor tutorHospital = new Tutor("Dr. Julian Morales " + sufijo, TipoTutor.HOSPITAL, null);
            tutorDAO.save(tutorUniversidad);
            tutorDAO.save(tutorHospital);
            System.out.println("4. Tabla tutor: tutores de universidad y hospital creados");

            Docente docenteUrgencias = new Docente("Dr. Carlos Ramirez " + sufijo, "Urgencias");
            Docente docentePediatria = new Docente("Dra. Sofia Lopez " + sufijo, "Pediatria");
            docenteDAO.save(docenteUrgencias);
            docenteDAO.save(docentePediatria);
            System.out.println("4.5. Tabla docente: docentes creados");

            Servicio servicioUrgencias = new Servicio("Servicio de Urgencias " + sufijo, urgencias);
            Servicio servicioPediatria = new Servicio("Servicio de Pediatria " + sufijo, pediatria);
            servicioDAO.save(servicioUrgencias);
            servicioDAO.save(servicioPediatria);
            System.out.println("4.6. Tabla servicio: servicios creados");

            Estudiante estudiantePrincipal = crearEstudianteCompleto(
                    "Ana Maria",
                    "Rojas Cardenas",
                    "DOC-" + sufijo,
                    universidad,
                    "ana.rojas" + sufijo + "@demo.edu.co",
                    "3001234567"
            );
            Estudiante estudianteApoyo = crearEstudianteCompleto(
                    "Luis Fernando",
                    "Perez Gomez",
                    "DOC-APOYO-" + sufijo,
                    universidad,
                    "luis.perez" + sufijo + "@demo.edu.co",
                    "3007654321"
            );
            estudianteController.crearEstudiante(estudiantePrincipal);
            estudianteController.crearEstudiante(estudianteApoyo);
            estudiantePrincipal.setInduccionCompleta(true);
            estudianteApoyo.setInduccionCompleta(true);
            estudianteController.actualizarEstudiante(estudiantePrincipal);
            estudianteController.actualizarEstudiante(estudianteApoyo);
            System.out.println("5. Tabla estudiante: usuarios creados con todos los campos diligenciados");

            TipoDocumento identidad = new TipoDocumento("Documento de identidad " + sufijo, true);
            TipoDocumento eps = new TipoDocumento("Afiliacion EPS " + sufijo, true);
            TipoDocumento carnetVacunacion = new TipoDocumento("Carnet de vacunacion " + sufijo, true);
            TipoDocumento autorizacion = new TipoDocumento("Autorizacion tratamiento datos " + sufijo, false);
            documentoService.crearTipoDocumento(identidad);
            documentoService.crearTipoDocumento(eps);
            documentoService.crearTipoDocumento(carnetVacunacion);
            documentoService.crearTipoDocumento(autorizacion);
            System.out.println("6. Tabla tipo_documento: documentos obligatorios y opcional creados");

            DocumentoEstudiante soporteVacunaPrincipal = registrarDocumentos(
                    documentoService,
                    tipoDocumentoDAO.findAll(),
                    estudiantePrincipal
            );
            DocumentoEstudiante soporteVacunaApoyo = registrarDocumentos(
                    documentoService,
                    tipoDocumentoDAO.findObligatorios(),
                    estudianteApoyo
            );
            System.out.println("7. Tabla documento_estudiante: documentos cargados y aprobados");

            Vacuna hepatitis = new Vacuna("Hepatitis B " + sufijo, 3, false);
            Vacuna covid = new Vacuna("COVID-19 " + sufijo, 2, true);
            Vacuna influenza = new Vacuna("Influenza " + sufijo, 1, true);
            vacunaService.crearVacuna(hepatitis);
            vacunaService.crearVacuna(covid);
            vacunaService.crearVacuna(influenza);
            registrarVacunasCompletas(vacunaService, estudiantePrincipal, soporteVacunaPrincipal, fechaPractica, hepatitis, covid, influenza);
            registrarVacunasCompletas(vacunaService, estudianteApoyo, soporteVacunaApoyo, fechaPractica, hepatitis, covid, influenza);
            System.out.println("8. Tablas vacuna y estudiante_vacuna: registros completos creados");

            Programacion programacionPrincipal = new Programacion(
                    estudiantePrincipal,
                    fechaPractica.getMonthValue(),
                    fechaPractica.getYear(),
                    docenteUrgencias
            );
            Programacion programacionApoyo = new Programacion(
                    estudianteApoyo,
                    fechaPractica.getMonthValue(),
                    fechaPractica.getYear(),
                    docentePediatria
            );
            programacionService.crearProgramacion(programacionPrincipal);
            programacionService.crearProgramacion(programacionApoyo);
            programacionService.agregarBloque(new ProgramacionDetalle(programacionPrincipal, fechaPractica, horaInicio, horaFin, urgencias));
            programacionService.agregarBloque(new ProgramacionDetalle(programacionApoyo, fechaPractica, horaInicio, horaFin, urgencias));
            System.out.println("9. Tablas programacion y programacion_detalle: bloques asignados en Urgencias");

            probarSolapamiento(programacionService, programacionPrincipal, fechaPractica, LocalTime.of(8, 30), LocalTime.of(10, 0), pediatria);

            Acceso ingreso = accesoController.registrarIngreso(estudiantePrincipal.getId(), horaIngreso);
            System.out.println("11. Tabla acceso: ingreso registrado. Acceso id: " + ingreso.getId());

            System.out.println("12. Estudiantes dentro del hospital: " + accesoController.contarEstudiantesDentro());
            for (Acceso acceso : accesoController.estudiantesDentro()) {
                System.out.println("    - " + acceso.getEstudiante().getNombres() + " " + acceso.getEstudiante().getApellidos());
            }

            Acceso salida = accesoController.registrarSalida(estudiantePrincipal.getId(), horaIngreso.plusMinutes(45));
            System.out.println("13. Salida registrada. Estado final: " + salida.getEstado());

            System.out.println("=== DEMO FINALIZADA: todas las tablas principales quedaron con datos ===");
        } finally {
            JPAUtil.close();
        }
    }

    private static Estudiante crearEstudianteCompleto(String nombres, String apellidos, String documento,
                                                       Universidad universidad, String correo, String celular) {
        Estudiante estudiante = new Estudiante(nombres, apellidos, documento, universidad);
        estudiante.setEstadoCivil("Soltera");
        estudiante.setFechaNacimiento(LocalDate.of(2001, 5, 18));
        estudiante.setLugarNacimiento("Tunja");
        estudiante.setDireccionTunja("Carrera 10 # 20-30");
        estudiante.setResidenciaPermanente("Sogamoso, Boyaca");
        estudiante.setCelular(celular);
        estudiante.setCorreo(correo);
        estudiante.setDireccionRepresentante("Calle 15 # 8-40");
        estudiante.setCiudadRepresentante("Tunja");
        estudiante.setNombreRepresentante("Carlos Rojas");
        estudiante.setParentesco("Padre");
        estudiante.setCelularRepresentante("3105557788");
        estudiante.setIdioma("Espanol e ingles basico");
        estudiante.setActividades("Lectura clinica, voluntariado y deporte recreativo");
        estudiante.setNombrePadre("Carlos Rojas");
        estudiante.setEdadPadre(52);
        estudiante.setNombreMadre("Patricia Cardenas");
        estudiante.setEdadMadre(49);
        estudiante.setTieneHijos(false);
        estudiante.setNombreHijos("No aplica");
        estudiante.setEdadesHijos("No aplica");
        estudiante.setTieneConyuge(false);
        estudiante.setNombreConyuge("No aplica");
        estudiante.setEdadConyuge(0);
        estudiante.setEnfermedadesGenerales("Niega enfermedades cronicas");
        estudiante.setEnfermedadesMentales("Niega antecedentes");
        estudiante.setMedicamentos("No toma medicamentos permanentes");
        estudiante.setAlergias("Niega alergias conocidas");
        estudiante.setPesoTallaImc("62 kg / 1.65 m / IMC 22.8");
        estudiante.setGrupoSanguineo("O+");
        estudiante.setCompanerosVivienda("Vive con dos companeras de universidad");
        estudiante.setNucleoFamiliar("Padre, madre y una hermana");
        estudiante.setPrograma("Enfermeria");
        estudiante.setFechaIngreso(LocalDate.of(2022, 2, 1));
        estudiante.setSemestre(8);
        estudiante.setPromedio(new BigDecimal("4.30"));
        estudiante.setInvestigacion("Proyecto de seguridad del paciente en urgencias");
        estudiante.setInduccionCompleta(false);
        estudiante.setVacunasCompletas(false);
        estudiante.setActivo(true);
        return estudiante;
    }

    private static DocumentoEstudiante registrarDocumentos(DocumentoService documentoService,
                                                            List<TipoDocumento> tipos,
                                                            Estudiante estudiante) {
        DocumentoEstudiante soporteVacuna = null;
        for (TipoDocumento tipo : tipos) {
            DocumentoEstudiante documento = new DocumentoEstudiante(
                    estudiante,
                    tipo,
                    tipo.getNombre().replace(' ', '_') + "_" + estudiante.getDocumento() + ".pdf",
                    "/documentos/" + estudiante.getDocumento() + "/" + tipo.getId() + ".pdf"
            );
            documentoService.registrarDocumento(documento);
            documentoService.validarDocumento(documento.getId(), EstadoDocumento.APROBADO, "Documento verificado en demo");
            if (soporteVacuna == null || tipo.getNombre().startsWith("Carnet de vacunacion")) {
                soporteVacuna = documento;
            }
        }
        return soporteVacuna;
    }

    private static void registrarVacunasCompletas(VacunaService vacunaService, Estudiante estudiante,
                                                   DocumentoEstudiante soporteVacuna, LocalDate fechaPractica,
                                                   Vacuna hepatitis, Vacuna covid, Vacuna influenza) {
        vacunaService.registrarVacunaEstudiante(new EstudianteVacuna(
                estudiante,
                hepatitis,
                3,
                fechaPractica.minusMonths(4),
                soporteVacuna,
                EstadoVacuna.COMPLETA
        ));
        vacunaService.registrarVacunaEstudiante(new EstudianteVacuna(
                estudiante,
                covid,
                2,
                fechaPractica.minusMonths(2),
                soporteVacuna,
                EstadoVacuna.COMPLETA
        ));
        vacunaService.registrarVacunaEstudiante(new EstudianteVacuna(
                estudiante,
                influenza,
                1,
                fechaPractica.minusMonths(1),
                soporteVacuna,
                EstadoVacuna.COMPLETA
        ));
    }

    private static void probarSolapamiento(ProgramacionService programacionService, Programacion programacion,
                                           LocalDate fecha, LocalTime inicio, LocalTime fin, Especialidad especialidad) {
        try {
            programacionService.agregarBloque(new ProgramacionDetalle(programacion, fecha, inicio, fin, especialidad));
            System.out.println("10. ERROR: el solapamiento fue permitido");
        } catch (BusinessException ex) {
            System.out.println("10. Solapamiento bloqueado correctamente: " + ex.getMessage());
        }
    }
}
