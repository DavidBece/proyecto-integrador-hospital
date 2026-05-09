package com.proyectohospital.services;

import java.time.LocalDateTime;
import java.util.List;

import com.proyectohospital.dao.interfaces.AccesoDAO;
import com.proyectohospital.dao.interfaces.EstudianteDAO;
import com.proyectohospital.entities.Acceso;
import com.proyectohospital.entities.EstadoAcceso;
import com.proyectohospital.entities.Estudiante;
import com.proyectohospital.utils.BusinessException;

public class AccesoService {
    private final AccesoDAO accesoDAO;
    private final EstudianteDAO estudianteDAO;
    private final ProgramacionService programacionService;
    private final DocumentoService documentoService;
    private final VacunaService vacunaService;

    public AccesoService(AccesoDAO accesoDAO, EstudianteDAO estudianteDAO, ProgramacionService programacionService,
                         DocumentoService documentoService, VacunaService vacunaService) {
        this.accesoDAO = accesoDAO;
        this.estudianteDAO = estudianteDAO;
        this.programacionService = programacionService;
        this.documentoService = documentoService;
        this.vacunaService = vacunaService;
    }

    public Acceso registrarIngreso(Long estudianteId, LocalDateTime momento) {
        Estudiante estudiante = estudianteDAO.findById(estudianteId);
        if (estudiante == null) {
            throw new BusinessException("No existe estudiante con id " + estudianteId);
        }
        validarIngreso(estudiante, momento);
        if (accesoDAO.findAccesoAbierto(estudianteId) != null) {
            throw new BusinessException("El estudiante ya se encuentra dentro del hospital");
        }
        Acceso acceso = new Acceso(estudiante, momento.toLocalDate(), momento, EstadoAcceso.DENTRO);
        accesoDAO.save(acceso);
        return acceso;
    }

    public Acceso registrarSalida(Long estudianteId, LocalDateTime momento) {
        Acceso acceso = accesoDAO.findAccesoAbierto(estudianteId);
        if (acceso == null) {
            throw new BusinessException("El estudiante no tiene un ingreso abierto");
        }
        acceso.setHoraSalida(momento);
        acceso.setEstado(EstadoAcceso.FUERA);
        return accesoDAO.update(acceso);
    }

    public void validarIngreso(Estudiante estudiante, LocalDateTime momento) {
        if (!Boolean.TRUE.equals(estudiante.getActivo())) {
            throw new BusinessException("El estudiante no está activo");
        }
        if (!Boolean.TRUE.equals(estudiante.getInduccionCompleta())) {
            throw new BusinessException("El estudiante no tiene inducción completa");
        }
        if (!programacionService.tieneProgramacionEnHorario(estudiante.getId(), momento.toLocalDate(), momento.toLocalTime())) {
            throw new BusinessException("El estudiante no tiene programación en este horario");
        }
        if (!documentoService.tieneDocumentosObligatoriosAprobados(estudiante)) {
            throw new BusinessException("El estudiante no tiene todos los documentos obligatorios aprobados");
        }
        if (!vacunaService.tieneVacunasCompletas(estudiante)) {
            throw new BusinessException("El estudiante no tiene vacunas completas");
        }
    }

    public List<Acceso> estudiantesDentro() {
        return accesoDAO.findEstudiantesDentro();
    }

    public long contarEstudiantesDentro() {
        return accesoDAO.countEstudiantesDentro();
    }
}
