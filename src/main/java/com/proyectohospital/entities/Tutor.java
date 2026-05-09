package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('UNIVERSIDAD','HOSPITAL')")
    private TipoTutor tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universidad_id")
    private Universidad universidad;

    public Tutor() {
    }

    public Tutor(String nombre, TipoTutor tipo, Universidad universidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.universidad = universidad;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public TipoTutor getTipo() { return tipo; }
    public void setTipo(TipoTutor tipo) { this.tipo = tipo; }
    public Universidad getUniversidad() { return universidad; }
    public void setUniversidad(Universidad universidad) { this.universidad = universidad; }
}
