package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "salud")
public class Salud {

    // =====================================================
    // ID
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salud")
    private Long idSalud;

    // =====================================================
    // INFORMACION MEDICA
    // =====================================================

    @Column(name = "enfermedades_generales", columnDefinition = "TEXT")
    private String enfermedadesGenerales;

    @Column(name = "enfermedades_mentales", columnDefinition = "TEXT")
    private String enfermedadesMentales;

    @Column(columnDefinition = "TEXT")
    private String medicamentos;

    @Column(columnDefinition = "TEXT")
    private String alergias;

    private Double peso;

    private Double talla;

    private Double imc;

    @Column(name = "grupo_sanguineo", length = 10)
    private String grupoSanguineo;

    // =====================================================
    // RELACION CON ESTUDIANTE
    // =====================================================

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Salud() {
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    public Long getIdSalud() {
        return idSalud;
    }

    public void setIdSalud(Long idSalud) {
        this.idSalud = idSalud;
    }

    public String getEnfermedadesGenerales() {
        return enfermedadesGenerales;
    }

    public void setEnfermedadesGenerales(String enfermedadesGenerales) {
        this.enfermedadesGenerales = enfermedadesGenerales;
    }

    public String getEnfermedadesMentales() {
        return enfermedadesMentales;
    }

    public void setEnfermedadesMentales(String enfermedadesMentales) {
        this.enfermedadesMentales = enfermedadesMentales;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getTalla() {
        return talla;
    }

    public void setTalla(Double talla) {
        this.talla = talla;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}