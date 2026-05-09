package com.proyectohospital.dao.impl;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.proyectohospital.dao.interfaces.ProgramacionDetalleDAO;
import com.proyectohospital.entities.ProgramacionDetalle;

public class ProgramacionDetalleDAOImpl extends GenericDAOImpl<ProgramacionDetalle, Long> implements ProgramacionDetalleDAO {
    public ProgramacionDetalleDAOImpl() {
        super(ProgramacionDetalle.class);
    }

    @Override
    public List<ProgramacionDetalle> findOverlapping(Long estudianteId, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("""
                            SELECT pd FROM ProgramacionDetalle pd
                            WHERE pd.programacion.estudiante.id = :estudianteId
                            AND pd.fecha = :fecha
                            AND :inicio < pd.horaFin
                            AND :fin > pd.horaInicio
                            """, ProgramacionDetalle.class)
                    .setParameter("estudianteId", estudianteId)
                    .setParameter("fecha", fecha)
                    .setParameter("inicio", inicio)
                    .setParameter("fin", fin)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ProgramacionDetalle> findByHorario(Long estudianteId, LocalDate fecha, LocalTime hora) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("""
                            SELECT pd FROM ProgramacionDetalle pd
                            WHERE pd.programacion.estudiante.id = :estudianteId
                            AND pd.fecha = :fecha
                            AND :hora BETWEEN pd.horaInicio AND pd.horaFin
                            """, ProgramacionDetalle.class)
                    .setParameter("estudianteId", estudianteId)
                    .setParameter("fecha", fecha)
                    .setParameter("hora", hora)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public long countByEspecialidadFechaHorario(Long especialidadId, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("""
                            SELECT COUNT(pd) FROM ProgramacionDetalle pd
                            WHERE pd.especialidad.id = :especialidadId
                            AND pd.fecha = :fecha
                            AND pd.horaInicio = :inicio
                            AND pd.horaFin = :fin
                            """, Long.class)
                    .setParameter("especialidadId", especialidadId)
                    .setParameter("fecha", fecha)
                    .setParameter("inicio", inicio)
                    .setParameter("fin", fin)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}
