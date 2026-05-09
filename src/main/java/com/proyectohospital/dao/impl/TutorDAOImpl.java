package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.TutorDAO;
import com.proyectohospital.entities.Tutor;

public class TutorDAOImpl extends GenericDAOImpl<Tutor, Long> implements TutorDAO {
    public TutorDAOImpl() {
        super(Tutor.class);
    }
}
