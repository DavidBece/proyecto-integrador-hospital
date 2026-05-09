package com.proyectohospital.dao.impl;

import com.proyectohospital.dao.interfaces.ProgramacionDAO;
import com.proyectohospital.entities.Programacion;

import jakarta.persistence.EntityManager;

public class ProgramacionDAOImpl extends GenericDAOImpl<Programacion, Long> implements ProgramacionDAO {
    public ProgramacionDAOImpl() {
        super(Programacion.class);
    }

    @Override
    public Programacion findByEstudianteMesAnio(Long estudianteId, int mes, int anio) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("""
                            SELECT p FROM Programacion p
                            WHERE p.estudiante.id = :estudianteId
                            AND p.mes = :mes
                            AND p.anio = :anio
                            """, Programacion.class)
                    .setParameter("estudianteId", estudianteId)
                    .setParameter("mes", mes)
                    .setParameter("anio", anio)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }
}
