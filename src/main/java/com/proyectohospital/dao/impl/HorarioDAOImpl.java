package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.HorarioDAO;
import com.proyectohospital.entities.Horario;
import jakarta.persistence.EntityManager;

public class HorarioDAOImpl extends GenericDAOImpl<Horario, Long> implements HorarioDAO {

    public HorarioDAOImpl() {
        super(Horario.class);
    }
}