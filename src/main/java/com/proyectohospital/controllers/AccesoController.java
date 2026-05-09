package com.proyectohospital.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.proyectohospital.entities.Acceso;
import com.proyectohospital.services.AccesoService;

public class AccesoController {

    private AccesoService accesoService;

    public AccesoController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

    public Acceso registrarIngreso(Long estudianteId, LocalDateTime horaIngreso) {
        return accesoService.registrarIngreso(estudianteId, horaIngreso);
    }

    public Acceso registrarSalida(Long estudianteId, LocalDateTime horaSalida) {
        return accesoService.registrarSalida(estudianteId, horaSalida);
    }

    public long contarEstudiantesDentro() {
        return accesoService.contarEstudiantesDentro();
    }

    public List<Acceso> estudiantesDentro() {
        return accesoService.estudiantesDentro();
    }
}