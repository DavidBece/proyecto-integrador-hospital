package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.ServicioDAO;
import com.proyectohospital.entities.Servicio;
import jakarta.persistence.EntityManager;

public class ServicioDAOImpl extends GenericDAOImpl<Servicio, Long> implements ServicioDAO {

    public ServicioDAOImpl() {
        super(Servicio.class);
    }
}