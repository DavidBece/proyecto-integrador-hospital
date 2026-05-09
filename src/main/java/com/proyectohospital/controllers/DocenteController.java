package com.proyectohospital.controllers;

import java.util.List;

import com.proyectohospital.entities.Docente;
import com.proyectohospital.services.DocenteService;

public class DocenteController {
    private final DocenteService docenteService;

    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    public void crearDocente(Docente docente) {
        docenteService.registrarDocente(docente);
    }

    public Docente buscarPorId(Long id) {
        return docenteService.obtenerDocente(id);
    }

    public List<Docente> listarDocentes() {
        return docenteService.obtenerTodosDocentes();
    }

    public void actualizarDocente(Docente docente) {
        docenteService.actualizarDocente(docente);
    }

    public void eliminarDocente(Long id) {
        docenteService.eliminarDocente(id);
    }
}