package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.UniversidadDAO;
import com.proyectohospital.entities.Universidad;

public class UniversidadDAOImpl extends GenericDAOImpl<Universidad, Long> implements UniversidadDAO {
    public UniversidadDAOImpl() {
        super(Universidad.class);
    }
}
