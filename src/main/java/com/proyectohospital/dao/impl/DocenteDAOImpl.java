package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.DocenteDAO;
import com.proyectohospital.entities.Docente;
import jakarta.persistence.EntityManager;

public class DocenteDAOImpl extends GenericDAOImpl<Docente, Long> implements DocenteDAO {

    public DocenteDAOImpl() {
        super(Docente.class);
    }
}