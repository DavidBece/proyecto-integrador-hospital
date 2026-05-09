package com.proyectohospital.controllers;

import java.util.List;

import com.proyectohospital.entities.Servicio;
import com.proyectohospital.services.ServicioService;

public class ServicioController {
    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    public void crearServicio(Servicio servicio) {
        servicioService.crearServicio(servicio);
    }

    public Servicio buscarPorId(Long id) {
        return servicioService.obtenerServicio(id);
    }

    public List<Servicio> listarServicios() {
        return servicioService.obtenerTodosServicios();
    }

    public void actualizarServicio(Servicio servicio) {
        servicioService.actualizarServicio(servicio);
    }

    public void eliminarServicio(Long id) {
        servicioService.eliminarServicio(id);
    }
}