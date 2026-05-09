package com.proyectohospital.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programa")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa")
    private Long idPrograma;

    @Column(nullable = false, length = 150)
    private String nombre;

    // =====================================================
    // RELACION CON UNIVERSIDAD
    // =====================================================

    @ManyToOne
    @JoinColumn(name = "id_universidad")
    private Universidad universidad;

    // =====================================================
    // RELACION CON DATOS ACADEMICOS
    // =====================================================

    @OneToMany(mappedBy = "programa")
    private List<DatosAcademicos> datosAcademicos = new ArrayList<>();

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Programa() {
    }

    public Programa(String nombre, Universidad universidad) {
        this.nombre = nombre;
        this.universidad = universidad;
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    public Long getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Long idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    public List<DatosAcademicos> getDatosAcademicos() {
        return datosAcademicos;
    }

    public void setDatosAcademicos(List<DatosAcademicos> datosAcademicos) {
        this.datosAcademicos = datosAcademicos;
    }
}