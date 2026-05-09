package com.proyectohospital.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "capacidad_practica", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"especialidad_id", "hora_inicio", "hora_fin"})
})
public class CapacidadPractica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(name = "capacidad_maxima", nullable = false)
    private Integer capacidadMaxima;

    private Boolean activa = true;

    public CapacidadPractica() {
    }

    public CapacidadPractica(Especialidad especialidad, LocalTime horaInicio, LocalTime horaFin, Integer capacidadMaxima) {
        this.especialidad = especialidad;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.capacidadMaxima = capacidadMaxima;
        this.activa = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public Integer getCapacidadMaxima() { return capacidadMaxima; }
    public void setCapacidadMaxima(Integer capacidadMaxima) { this.capacidadMaxima = capacidadMaxima; }
    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
}
