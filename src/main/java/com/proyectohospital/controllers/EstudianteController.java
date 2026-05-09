package com.proyectohospital.controllers;

import java.util.List;

import com.proyectohospital.entities.Estudiante;
import com.proyectohospital.services.EstudianteService;

public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    public void crearEstudiante(Estudiante estudiante) {
        estudianteService.crear(estudiante);
    }

    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        return estudianteService.actualizar(estudiante);
    }

    public Estudiante buscarPorId(Long id) {
        return estudianteService.buscarPorId(id);
    }

    public Estudiante buscarPorDocumento(String documento) {
        return estudianteService.buscarPorDocumento(documento);
    }

    public List<Estudiante> listarEstudiantes() {
        return estudianteService.listar();
    }

    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteService.eliminar(estudiante);
    }
}
