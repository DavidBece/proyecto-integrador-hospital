package com.proyectohospital.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "datos_academicos")
public class DatosAcademicos {

    // =====================================================
    // ID
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_academicos")
    private Long idDatosAcademicos;

    // =====================================================
    // INFORMACION ACADEMICA
    // =====================================================

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "semestre")
    private Integer semestre;

    @Column(name = "promedio_acumulado", precision = 4, scale = 2)
    private BigDecimal promedioAcumulado;

    @Column(columnDefinition = "TEXT")
    private String investigacion;

    // =====================================================
    // RELACION CON UNIVERSIDAD
    // =====================================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_universidad")
    private Universidad universidad;

    // =====================================================
    // RELACION CON PROGRAMA
    // =====================================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa")
    private Programa programa;

    // =====================================================
    // RELACION UNO A UNO CON ESTUDIANTE
    // =====================================================

    @OneToOne
    private Estudiante estudiante;


    // =====================================================
    // CONSTRUCTORES
    // =====================================================

    public DatosAcademicos() {
    }

    public DatosAcademicos(
            LocalDate fechaIngreso,
            Integer semestre,
            BigDecimal promedioAcumulado,
            String investigacion,
            Universidad universidad,
            Programa programa
    ) {
        this.fechaIngreso = fechaIngreso;
        this.semestre = semestre;
        this.promedioAcumulado = promedioAcumulado;
        this.investigacion = investigacion;
        this.universidad = universidad;
        this.programa = programa;
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    public Long getIdDatosAcademicos() {
        return idDatosAcademicos;
    }

    public void setIdDatosAcademicos(Long idDatosAcademicos) {
        this.idDatosAcademicos = idDatosAcademicos;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public BigDecimal getPromedioAcumulado() {
        return promedioAcumulado;
    }

    public void setPromedioAcumulado(BigDecimal promedioAcumulado) {
        this.promedioAcumulado = promedioAcumulado;
    }

    public String getInvestigacion() {
        return investigacion;
    }

    public void setInvestigacion(String investigacion) {
        this.investigacion = investigacion;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}