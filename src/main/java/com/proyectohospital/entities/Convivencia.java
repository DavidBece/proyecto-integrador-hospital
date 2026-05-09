package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "convivencia")
public class Convivencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convivencia")
    private Long idConvivencia;

    // =====================================================
    // INFORMACION DE CONVIVENCIA
    // =====================================================

    @Column(name = "companeros_vivienda", columnDefinition = "TEXT")
    private String companerosVivienda;

    @Column(name = "nucleo_familiar", columnDefinition = "TEXT")
    private String nucleoFamiliar;

    // =====================================================
    // RELACION UNO A UNO CON ESTUDIANTE
    // =====================================================

    // El lado inverso en Estudiante ya no existe con el atributo `convivencia`.
    // Para evitar error de Hibernate (mappedBy apuntando a una propiedad inexistente),
    // se usa relación unidireccional: Convivencia mantiene el reference a Estudiante.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Convivencia() {
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    public Long getIdConvivencia() {
        return idConvivencia;
    }

    public void setIdConvivencia(Long idConvivencia) {
        this.idConvivencia = idConvivencia;
    }

    public String getCompanerosVivienda() {
        return companerosVivienda;
    }

    public void setCompanerosVivienda(String companerosVivienda) {
        this.companerosVivienda = companerosVivienda;
    }

    public String getNucleoFamiliar() {
        return nucleoFamiliar;
    }

    public void setNucleoFamiliar(String nucleoFamiliar) {
        this.nucleoFamiliar = nucleoFamiliar;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}