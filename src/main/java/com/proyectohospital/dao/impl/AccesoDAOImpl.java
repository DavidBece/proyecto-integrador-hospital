package com.proyectohospital.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;

import com.proyectohospital.dao.interfaces.AccesoDAO;
import com.proyectohospital.entities.Acceso;
import com.proyectohospital.entities.EstadoAcceso;

public class AccesoDAOImpl
        extends GenericDAOImpl<Acceso, Long>
        implements AccesoDAO {

    public AccesoDAOImpl() {
        super(Acceso.class);
    }

    @Override
    public Acceso findAccesoAbierto(Long estudianteId) {

        EntityManager em = entityManager();

        try {

            return em.createQuery("""
                            SELECT a FROM Acceso a
                            WHERE a.estudiante.id = :estudianteId
                            AND a.estado = :estado
                            ORDER BY a.horaIngreso DESC
                            """, Acceso.class)
                    .setParameter("estudianteId", estudianteId)
                    .setParameter("estado", EstadoAcceso.DENTRO)
                    .setMaxResults(1)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

        } finally {
            em.close();
        }
    }

    @Override
    public List<Acceso> findEstudiantesDentro() {

        EntityManager em = entityManager();

        try {

            return em.createQuery("""
                    SELECT a FROM Acceso a
                    JOIN FETCH a.estudiante
                    WHERE a.estado = :estado
                    """, Acceso.class)
                    .setParameter("estado", EstadoAcceso.DENTRO)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public long countEstudiantesDentro() {

        EntityManager em = entityManager();

        try {

            return em.createQuery("""
                    SELECT COUNT(a)
                    FROM Acceso a
                    WHERE a.estado = :estado
                    """, Long.class)
                    .setParameter("estado", EstadoAcceso.DENTRO)
                    .getSingleResult();

        } finally {
            em.close();
        }
    }
}