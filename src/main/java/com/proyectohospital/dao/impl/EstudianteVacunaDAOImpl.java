package com.proyectohospital.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;

import com.proyectohospital.dao.interfaces.EstudianteVacunaDAO;
import com.proyectohospital.entities.EstudianteVacuna;

public class EstudianteVacunaDAOImpl extends GenericDAOImpl<EstudianteVacuna, Long> implements EstudianteVacunaDAO {
    public EstudianteVacunaDAOImpl() {
        super(EstudianteVacuna.class);
    }

    @Override
    public EstudianteVacuna findByEstudianteAndVacuna(Long estudianteId, Long vacunaId) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("""
                            SELECT ev FROM EstudianteVacuna ev
                            WHERE ev.estudiante.id = :estudianteId
                            AND ev.vacuna.id = :vacunaId
                            """, EstudianteVacuna.class)
                    .setParameter("estudianteId", estudianteId)
                    .setParameter("vacunaId", vacunaId)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }

    @Override
    public List<EstudianteVacuna> findByEstudiante(Long estudianteId) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("SELECT ev FROM EstudianteVacuna ev WHERE ev.estudiante.id = :estudianteId", EstudianteVacuna.class)
                    .setParameter("estudianteId", estudianteId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
