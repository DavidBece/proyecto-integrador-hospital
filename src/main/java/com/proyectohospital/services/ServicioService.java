package com.proyectohospital.services;

import com.proyectohospital.dao.impl.ServicioDAOImpl;
import com.proyectohospital.entities.Servicio;
import com.proyectohospital.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ServicioService {

    private ServicioDAOImpl servicioDAO;

    public ServicioService() {
        this.servicioDAO = new ServicioDAOImpl();
    }

    public void crearServicio(Servicio servicio) {
        servicioDAO.save(servicio);
    }

    public Servicio obtenerServicio(Long id) {
        return servicioDAO.findById(id);
    }

    public List<Servicio> obtenerTodosServicios() {
        return servicioDAO.findAll();
    }

    public void actualizarServicio(Servicio servicio) {
        servicioDAO.update(servicio);
    }

    public void eliminarServicio(Long id) {
        Servicio servicio = servicioDAO.findById(id);
        if (servicio != null) {
            servicioDAO.delete(servicio);
        }
    }
}