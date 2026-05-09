package com.proyectohospital.dao.impl;

import jakarta.persistence.EntityManager;
import java.time.LocalTime;

import com.proyectohospital.dao.interfaces.CapacidadPracticaDAO;
import com.proyectohospital.entities.CapacidadPractica;

public class CapacidadPracticaDAOImpl extends GenericDAOImpl<CapacidadPractica, Long> implements CapacidadPracticaDAO {
    public CapacidadPracticaDAOImpl() {
        super(CapacidadPractica.class);
    }

    @Override
    public CapacidadPractica findActivaByEspecialidadAndHorario(Long especialidadId, LocalTime inicio, LocalTime fin) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("""
                            SELECT cp FROM CapacidadPractica cp
                            WHERE cp.especialidad.id = :especialidadId
                            AND cp.horaInicio = :inicio
                            AND cp.horaFin = :fin
                            AND cp.activa = true
                            """, CapacidadPractica.class)
                    .setParameter("especialidadId", especialidadId)
                    .setParameter("inicio", inicio)
                    .setParameter("fin", fin)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }
}
