package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.RegistroIngresoDAO;
import com.proyectohospital.entities.RegistroIngreso;
import jakarta.persistence.EntityManager;

public class RegistroIngresoDAOImpl extends GenericDAOImpl<RegistroIngreso, Long> implements RegistroIngresoDAO {

    public RegistroIngresoDAOImpl() {
        super(RegistroIngreso.class);
    }
}