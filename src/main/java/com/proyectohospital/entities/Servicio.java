package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_area_medica")
    private Especialidad areaMedica;

    public Servicio() {
    }

    public Servicio(String nombre, Especialidad areaMedica) {
        this.nombre = nombre;
        this.areaMedica = areaMedica;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Especialidad getAreaMedica() { return areaMedica; }
    public void setAreaMedica(Especialidad areaMedica) { this.areaMedica = areaMedica; }
}