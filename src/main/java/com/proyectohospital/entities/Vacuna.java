package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vacuna")
public class Vacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "dosis_requeridas")
    private Integer dosisRequeridas;

    @Column(name = "requiere_refuerzo")
    private Boolean requiereRefuerzo;

    public Vacuna() {
    }

    public Vacuna(String nombre, Integer dosisRequeridas, Boolean requiereRefuerzo) {
        this.nombre = nombre;
        this.dosisRequeridas = dosisRequeridas;
        this.requiereRefuerzo = requiereRefuerzo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getDosisRequeridas() { return dosisRequeridas; }
    public void setDosisRequeridas(Integer dosisRequeridas) { this.dosisRequeridas = dosisRequeridas; }
    public Boolean getRequiereRefuerzo() { return requiereRefuerzo; }
    public void setRequiereRefuerzo(Boolean requiereRefuerzo) { this.requiereRefuerzo = requiereRefuerzo; }
}
