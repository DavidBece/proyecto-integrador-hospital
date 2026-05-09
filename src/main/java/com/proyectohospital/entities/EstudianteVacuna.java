package com.proyectohospital.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "estudiante_vacuna", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"estudiante_id", "vacuna_id"})
})
public class EstudianteVacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vacuna_id", nullable = false)
    private Vacuna vacuna;

    @Column(name = "dosis_aplicadas")
    private Integer dosisAplicadas;

    @Column(name = "fecha_ultima_dosis")
    private LocalDate fechaUltimaDosis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soporte_documento_id")
    private DocumentoEstudiante soporteDocumento;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INCOMPLETA','COMPLETA','PENDIENTE_VALIDACION')")
    private EstadoVacuna estado;

    public EstudianteVacuna() {
    }

    public EstudianteVacuna(Estudiante estudiante, Vacuna vacuna, Integer dosisAplicadas, LocalDate fechaUltimaDosis, DocumentoEstudiante soporteDocumento, EstadoVacuna estado) {
        this.estudiante = estudiante;
        this.vacuna = vacuna;
        this.dosisAplicadas = dosisAplicadas;
        this.fechaUltimaDosis = fechaUltimaDosis;
        this.soporteDocumento = soporteDocumento;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }
    public Vacuna getVacuna() { return vacuna; }
    public void setVacuna(Vacuna vacuna) { this.vacuna = vacuna; }
    public Integer getDosisAplicadas() { return dosisAplicadas; }
    public void setDosisAplicadas(Integer dosisAplicadas) { this.dosisAplicadas = dosisAplicadas; }
    public LocalDate getFechaUltimaDosis() { return fechaUltimaDosis; }
    public void setFechaUltimaDosis(LocalDate fechaUltimaDosis) { this.fechaUltimaDosis = fechaUltimaDosis; }
    public DocumentoEstudiante getSoporteDocumento() { return soporteDocumento; }
    public void setSoporteDocumento(DocumentoEstudiante soporteDocumento) { this.soporteDocumento = soporteDocumento; }
    public EstadoVacuna getEstado() { return estado; }
    public void setEstado(EstadoVacuna estado) { this.estado = estado; }
}
