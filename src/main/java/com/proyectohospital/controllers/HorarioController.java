package com.proyectohospital.controllers;

import java.util.List;

import com.proyectohospital.entities.Horario;
import com.proyectohospital.services.HorarioService;

public class HorarioController {
    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public void asignarHorario(Horario horario) {
        horarioService.asignarHorario(horario);
    }

    public Horario buscarPorId(Long id) {
        return horarioService.obtenerHorario(id);
    }

    public List<Horario> listarHorarios() {
        return horarioService.obtenerTodosHorarios();
    }

    public void actualizarHorario(Horario horario) {
        horarioService.actualizarHorario(horario);
    }
}