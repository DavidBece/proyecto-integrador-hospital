package com.proyectohospital.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "programacion", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"estudiante_id", "mes", "anio"})
})
public class Programacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @Column(nullable = false)
    private Integer mes;

    @Column(nullable = false)
    private Integer anio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_docente")
    private Docente docente;

    @OneToMany(mappedBy = "programacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramacionDetalle> detalles = new ArrayList<>();

    public Programacion() {
    }

    public Programacion(Estudiante estudiante, Integer mes, Integer anio, Docente docente) {
        this.estudiante = estudiante;
        this.mes = mes;
        this.anio = anio;
        this.docente = docente;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }
    public Docente getDocente() { return docente; }
    public void setDocente(Docente docente) { this.docente = docente; }
    public List<ProgramacionDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<ProgramacionDetalle> detalles) { this.detalles = detalles; }
}
