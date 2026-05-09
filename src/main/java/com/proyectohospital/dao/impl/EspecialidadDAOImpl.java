package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.EspecialidadDAO;
import com.proyectohospital.entities.Especialidad;

public class EspecialidadDAOImpl extends GenericDAOImpl<Especialidad, Long> implements EspecialidadDAO {
    public EspecialidadDAOImpl() {
        super(Especialidad.class);
    }
}
