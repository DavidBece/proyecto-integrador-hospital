package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.VacunaDAO;
import com.proyectohospital.entities.Vacuna;

public class VacunaDAOImpl extends GenericDAOImpl<Vacuna, Long> implements VacunaDAO {
    public VacunaDAOImpl() {
        super(Vacuna.class);
    }
}
