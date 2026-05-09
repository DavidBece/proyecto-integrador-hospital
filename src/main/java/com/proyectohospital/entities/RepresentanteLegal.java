package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "representante_legal")
public class RepresentanteLegal {

    // =====================================================
    // ID
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_representante")
    private Long idRepresentante;

    // =====================================================
    // INFORMACION REPRESENTANTE
    // =====================================================

    @Column(name = "nombre_representante", length = 150)
    private String nombreRepresentante;

    @Column(name = "parentesco_representante", length = 100)
    private String parentescoRepresentante;

    @Column(name = "celular_representante", length = 20)
    private String celularRepresentante;

    @Column(name = "direccion_representante", length = 200)
    private String direccionRepresentante;

    @Column(name = "ciudad_representante", length = 100)
    private String ciudadRepresentante;

    // =====================================================
    // RELACION CON ESTUDIANTE
    // =====================================================

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public RepresentanteLegal() {
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    public Long getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Long idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getParentescoRepresentante() {
        return parentescoRepresentante;
    }

    public void setParentescoRepresentante(String parentescoRepresentante) {
        this.parentescoRepresentante = parentescoRepresentante;
    }

    public String getCelularRepresentante() {
        return celularRepresentante;
    }

    public void setCelularRepresentante(String celularRepresentante) {
        this.celularRepresentante = celularRepresentante;
    }

    public String getDireccionRepresentante() {
        return direccionRepresentante;
    }

    public void setDireccionRepresentante(String direccionRepresentante) {
        this.direccionRepresentante = direccionRepresentante;
    }

    public String getCiudadRepresentante() {
        return ciudadRepresentante;
    }

    public void setCiudadRepresentante(String ciudadRepresentante) {
        this.ciudadRepresentante = ciudadRepresentante;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}