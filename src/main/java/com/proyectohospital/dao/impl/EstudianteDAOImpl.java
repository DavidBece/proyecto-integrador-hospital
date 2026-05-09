package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.EstudianteDAO;
import com.proyectohospital.entities.Estudiante;

import jakarta.persistence.EntityManager;

public class EstudianteDAOImpl extends GenericDAOImpl<Estudiante, Long> implements EstudianteDAO {
    public EstudianteDAOImpl() {
        super(Estudiante.class);
    }

    @Override
    public Estudiante findByDocumento(String documento) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("SELECT e FROM Estudiante e WHERE e.documento = :documento", Estudiante.class)
                    .setParameter("documento", documento)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }
}
