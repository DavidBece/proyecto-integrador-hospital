package com.proyectohospital.controllers;

import java.util.List;

import com.proyectohospital.entities.RegistroIngreso;
import com.proyectohospital.services.RegistroIngresoService;

public class RegistroIngresoController {
    private final RegistroIngresoService registroIngresoService;

    public RegistroIngresoController(RegistroIngresoService registroIngresoService) {
        this.registroIngresoService = registroIngresoService;
    }

    public void registrarIngreso(RegistroIngreso registro) {
        registroIngresoService.registrarIngreso(registro);
    }

    public RegistroIngreso buscarPorId(Long id) {
        return registroIngresoService.obtenerRegistro(id);
    }

    public List<RegistroIngreso> listarRegistros() {
        return registroIngresoService.obtenerTodosRegistros();
    }

    public void actualizarRegistro(RegistroIngreso registro) {
        registroIngresoService.actualizarRegistro(registro);
    }

    public void eliminarRegistro(Long id) {
        registroIngresoService.eliminarRegistro(id);
    }
}