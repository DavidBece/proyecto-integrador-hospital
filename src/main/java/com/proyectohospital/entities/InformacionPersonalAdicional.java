package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "informacion_personal_adicional")
public class InformacionPersonalAdicional {

    // =====================================================
    // ID
    // =====================================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_info_personal")
    private Long idInfoPersonal;

    // =====================================================
    // INFORMACION PERSONAL
    // =====================================================

    @Column(name = "idioma_adicional", length = 100)
    private String idiomaAdicional;

    @Column(name = "actividades_complementarias", columnDefinition = "TEXT")
    private String actividadesComplementarias;

    // =====================================================
    // RELACION CON ESTUDIANTE
    // =====================================================

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public InformacionPersonalAdicional() {
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    public Long getIdInfoPersonal() {
        return idInfoPersonal;
    }

    public void setIdInfoPersonal(Long idInfoPersonal) {
        this.idInfoPersonal = idInfoPersonal;
    }

    public String getIdiomaAdicional() {
        return idiomaAdicional;
    }

    public void setIdiomaAdicional(String idiomaAdicional) {
        this.idiomaAdicional = idiomaAdicional;
    }

    public String getActividadesComplementarias() {
        return actividadesComplementarias;
    }

    public void setActividadesComplementarias(String actividadesComplementarias) {
        this.actividadesComplementarias = actividadesComplementarias;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}