package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "informacion_familiar")
public class InformacionFamiliar {

    // =====================================================
    // ID
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informacion_familiar")
    private Long idInformacionFamiliar;

    // =====================================================
    // DATOS PADRE
    // =====================================================

    @Column(name = "nombre_padre", length = 150)
    private String nombrePadre;

    @Column(name = "edad_padre")
    private Integer edadPadre;

    // =====================================================
    // DATOS MADRE
    // =====================================================

    @Column(name = "nombre_madre", length = 150)
    private String nombreMadre;

    @Column(name = "edad_madre")
    private Integer edadMadre;

    // =====================================================
    // HIJOS
    // =====================================================

    @Column(name = "tiene_hijos")
    private Boolean tieneHijos;

    @Column(name = "nombre_hijos", columnDefinition = "TEXT")
    private String nombreHijos;

    @Column(name = "edades_hijos", columnDefinition = "TEXT")
    private String edadesHijos;

    // =====================================================
    // CONYUGE
    // =====================================================

    @Column(name = "tiene_conyuge")
    private Boolean tieneConyuge;

    @Column(name = "nombre_conyuge", length = 150)
    private String nombreConyuge;

    @Column(name = "edad_conyuge")
    private Integer edadConyuge;

    // =====================================================
    // RELACION CON ESTUDIANTE
    // =====================================================

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public InformacionFamiliar() {
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    public Long getIdInformacionFamiliar() {
        return idInformacionFamiliar;
    }

    public void setIdInformacionFamiliar(Long idInformacionFamiliar) {
        this.idInformacionFamiliar = idInformacionFamiliar;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public Integer getEdadPadre() {
        return edadPadre;
    }

    public void setEdadPadre(Integer edadPadre) {
        this.edadPadre = edadPadre;
    }

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public Integer getEdadMadre() {
        return edadMadre;
    }

    public void setEdadMadre(Integer edadMadre) {
        this.edadMadre = edadMadre;
    }

    public Boolean getTieneHijos() {
        return tieneHijos;
    }

    public void setTieneHijos(Boolean tieneHijos) {
        this.tieneHijos = tieneHijos;
    }

    public String getNombreHijos() {
        return nombreHijos;
    }

    public void setNombreHijos(String nombreHijos) {
        this.nombreHijos = nombreHijos;
    }

    public String getEdadesHijos() {
        return edadesHijos;
    }

    public void setEdadesHijos(String edadesHijos) {
        this.edadesHijos = edadesHijos;
    }

    public Boolean getTieneConyuge() {
        return tieneConyuge;
    }

    public void setTieneConyuge(Boolean tieneConyuge) {
        this.tieneConyuge = tieneConyuge;
    }

    public String getNombreConyuge() {
        return nombreConyuge;
    }

    public void setNombreConyuge(String nombreConyuge) {
        this.nombreConyuge = nombreConyuge;
    }

    public Integer getEdadConyuge() {
        return edadConyuge;
    }

    public void setEdadConyuge(Integer edadConyuge) {
        this.edadConyuge = edadConyuge;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}