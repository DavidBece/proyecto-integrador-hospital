package com.proyectohospital.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombres;

    @Column(nullable = false, length = 150)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 50)
    private String documento;

    @Column(name = "estado_civil", length = 50)
    private String estadoCivil;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "lugar_nacimiento", length = 100)
    private String lugarNacimiento;

    @Column(name = "direccion_tunja", length = 200)
    private String direccionTunja;

    @Column(name = "residencia_permanente", length = 200)
    private String residenciaPermanente;

    @Column(length = 20)
    private String celular;

    @Column(length = 150)
    private String correo;

    @Column(name = "direccion_representante", length = 200)
    private String direccionRepresentante;

    @Column(name = "ciudad_representante", length = 100)
    private String ciudadRepresentante;

    @Column(name = "nombre_representante", length = 150)
    private String nombreRepresentante;

    @Column(length = 50)
    private String parentesco;

    @Column(name = "celular_representante", length = 20)
    private String celularRepresentante;

    @Column(length = 100)
    private String idioma;

    @Column(columnDefinition = "TEXT")
    private String actividades;

    @Column(name = "nombre_padre", length = 150)
    private String nombrePadre;

    @Column(name = "edad_padre")
    private Integer edadPadre;

    @Column(name = "nombre_madre", length = 150)
    private String nombreMadre;

    @Column(name = "edad_madre")
    private Integer edadMadre;

    @Column(name = "tiene_hijos")
    private Boolean tieneHijos;

    @Column(name = "nombre_hijos", columnDefinition = "TEXT")
    private String nombreHijos;

    @Column(name = "edades_hijos", columnDefinition = "TEXT")
    private String edadesHijos;

    @Column(name = "tiene_conyuge")
    private Boolean tieneConyuge;

    @Column(name = "nombre_conyuge", length = 150)
    private String nombreConyuge;

    @Column(name = "edad_conyuge")
    private Integer edadConyuge;

    @Column(name = "enfermedades_generales", columnDefinition = "TEXT")
    private String enfermedadesGenerales;

    @Column(name = "enfermedades_mentales", columnDefinition = "TEXT")
    private String enfermedadesMentales;

    @Column(columnDefinition = "TEXT")
    private String medicamentos;

    @Column(columnDefinition = "TEXT")
    private String alergias;

    @Column(name = "peso_talla_imc", length = 50)
    private String pesoTallaImc;

    @Column(name = "grupo_sanguineo", length = 10)
    private String grupoSanguineo;

    @Column(name = "companeros_vivienda", columnDefinition = "TEXT")
    private String companerosVivienda;

    @Column(name = "nucleo_familiar", columnDefinition = "TEXT")
    private String nucleoFamiliar;

    @Column(length = 150)
    private String programa;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    private Integer semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universidad_id")
    private Universidad universidad;

    @Column(precision = 4, scale = 2)
    private BigDecimal promedio;

    @Column(columnDefinition = "TEXT")
    private String investigacion;

    @Column(name = "induccion_completa")
    private Boolean induccionCompleta = false;

    @Column(name = "vacunas_completas")
    private Boolean vacunasCompletas = false;

    private Boolean activo = true;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<Programacion> programaciones = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<DocumentoEstudiante> documentos = new ArrayList<>();

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<EstudianteVacuna> vacunas = new ArrayList<>();

    @OneToOne
    private DatosPersonales datosPersonales;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<Acceso> accesos = new ArrayList<>();

    public Estudiante() {
    }

    public Estudiante(String nombres, String apellidos, String documento, Universidad universidad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.universidad = universidad;
        this.activo = true;
        this.induccionCompleta = false;
        this.vacunasCompletas = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getLugarNacimiento() { return lugarNacimiento; }
    public void setLugarNacimiento(String lugarNacimiento) { this.lugarNacimiento = lugarNacimiento; }
    public String getDireccionTunja() { return direccionTunja; }
    public void setDireccionTunja(String direccionTunja) { this.direccionTunja = direccionTunja; }
    public String getResidenciaPermanente() { return residenciaPermanente; }
    public void setResidenciaPermanente(String residenciaPermanente) { this.residenciaPermanente = residenciaPermanente; }
    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getDireccionRepresentante() { return direccionRepresentante; }
    public void setDireccionRepresentante(String direccionRepresentante) { this.direccionRepresentante = direccionRepresentante; }
    public String getCiudadRepresentante() { return ciudadRepresentante; }
    public void setCiudadRepresentante(String ciudadRepresentante) { this.ciudadRepresentante = ciudadRepresentante; }
    public String getNombreRepresentante() { return nombreRepresentante; }
    public void setNombreRepresentante(String nombreRepresentante) { this.nombreRepresentante = nombreRepresentante; }
    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }
    public String getCelularRepresentante() { return celularRepresentante; }
    public void setCelularRepresentante(String celularRepresentante) { this.celularRepresentante = celularRepresentante; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public String getActividades() { return actividades; }
    public void setActividades(String actividades) { this.actividades = actividades; }
    public String getNombrePadre() { return nombrePadre; }
    public void setNombrePadre(String nombrePadre) { this.nombrePadre = nombrePadre; }
    public Integer getEdadPadre() { return edadPadre; }
    public void setEdadPadre(Integer edadPadre) { this.edadPadre = edadPadre; }
    public String getNombreMadre() { return nombreMadre; }
    public void setNombreMadre(String nombreMadre) { this.nombreMadre = nombreMadre; }
    public Integer getEdadMadre() { return edadMadre; }
    public void setEdadMadre(Integer edadMadre) { this.edadMadre = edadMadre; }
    public Boolean getTieneHijos() { return tieneHijos; }
    public void setTieneHijos(Boolean tieneHijos) { this.tieneHijos = tieneHijos; }
    public String getNombreHijos() { return nombreHijos; }
    public void setNombreHijos(String nombreHijos) { this.nombreHijos = nombreHijos; }
    public String getEdadesHijos() { return edadesHijos; }
    public void setEdadesHijos(String edadesHijos) { this.edadesHijos = edadesHijos; }
    public Boolean getTieneConyuge() { return tieneConyuge; }
    public void setTieneConyuge(Boolean tieneConyuge) { this.tieneConyuge = tieneConyuge; }
    public String getNombreConyuge() { return nombreConyuge; }
    public void setNombreConyuge(String nombreConyuge) { this.nombreConyuge = nombreConyuge; }
    public Integer getEdadConyuge() { return edadConyuge; }
    public void setEdadConyuge(Integer edadConyuge) { this.edadConyuge = edadConyuge; }
    public String getEnfermedadesGenerales() { return enfermedadesGenerales; }
    public void setEnfermedadesGenerales(String enfermedadesGenerales) { this.enfermedadesGenerales = enfermedadesGenerales; }
    public String getEnfermedadesMentales() { return enfermedadesMentales; }
    public void setEnfermedadesMentales(String enfermedadesMentales) { this.enfermedadesMentales = enfermedadesMentales; }
    public String getMedicamentos() { return medicamentos; }
    public void setMedicamentos(String medicamentos) { this.medicamentos = medicamentos; }
    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }
    public String getPesoTallaImc() { return pesoTallaImc; }
    public void setPesoTallaImc(String pesoTallaImc) { this.pesoTallaImc = pesoTallaImc; }
    public String getGrupoSanguineo() { return grupoSanguineo; }
    public void setGrupoSanguineo(String grupoSanguineo) { this.grupoSanguineo = grupoSanguineo; }
    public String getCompanerosVivienda() { return companerosVivienda; }
    public void setCompanerosVivienda(String companerosVivienda) { this.companerosVivienda = companerosVivienda; }
    public String getNucleoFamiliar() { return nucleoFamiliar; }
    public void setNucleoFamiliar(String nucleoFamiliar) { this.nucleoFamiliar = nucleoFamiliar; }
    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }
    public Universidad getUniversidad() { return universidad; }
    public void setUniversidad(Universidad universidad) { this.universidad = universidad; }
    public BigDecimal getPromedio() { return promedio; }
    public void setPromedio(BigDecimal promedio) { this.promedio = promedio; }
    public String getInvestigacion() { return investigacion; }
    public void setInvestigacion(String investigacion) { this.investigacion = investigacion; }
    public Boolean getInduccionCompleta() { return induccionCompleta; }
    public void setInduccionCompleta(Boolean induccionCompleta) { this.induccionCompleta = induccionCompleta; }
    public Boolean getVacunasCompletas() { return vacunasCompletas; }
    public void setVacunasCompletas(Boolean vacunasCompletas) { this.vacunasCompletas = vacunasCompletas; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public List<Programacion> getProgramaciones() { return programaciones; }
    public void setProgramaciones(List<Programacion> programaciones) { this.programaciones = programaciones; }
    public List<DocumentoEstudiante> getDocumentos() { return documentos; }
    public void setDocumentos(List<DocumentoEstudiante> documentos) { this.documentos = documentos; }
    public List<EstudianteVacuna> getVacunas() { return vacunas; }
    public void setVacunas(List<EstudianteVacuna> vacunas) { this.vacunas = vacunas; }
    public List<Acceso> getAccesos() { return accesos; }
    public void setAccesos(List<Acceso> accesos) { this.accesos = accesos; }
}
