package com.proyectohospital.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Long idContacto;

    // =====================================================
    // INFORMACION DE CONTACTO
    // =====================================================

    @Column(name = "direccion_tunja", length = 200)
    private String direccionTunja;

    @Column(name = "residencia_permanente", length = 200)
    private String residenciaPermanente;

    @Column(length = 20)
    private String celular;

    @Column(name = "correo_electronico", length = 150)
    private String correoElectronico;

    // =====================================================
    // RELACION UNO A UNO CON ESTUDIANTE
    // =====================================================

    @OneToOne
    private Estudiante estudiante;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public Contacto() {
    }

    // =====================================================
    // GETTERS Y SETTERS
    // =====================================================

    public Long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Long idContacto) {
        this.idContacto = idContacto;
    }

    public String getDireccionTunja() {
        return direccionTunja;
    }

    public void setDireccionTunja(String direccionTunja) {
        this.direccionTunja = direccionTunja;
    }

    public String getResidenciaPermanente() {
        return residenciaPermanente;
    }

    public void setResidenciaPermanente(String residenciaPermanente) {
        this.residenciaPermanente = residenciaPermanente;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}