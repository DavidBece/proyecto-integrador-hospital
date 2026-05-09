package com.proyectohospital.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;

import com.proyectohospital.dao.interfaces.DocumentoEstudianteDAO;
import com.proyectohospital.entities.DocumentoEstudiante;

public class DocumentoEstudianteDAOImpl extends GenericDAOImpl<DocumentoEstudiante, Long> implements DocumentoEstudianteDAO {
    public DocumentoEstudianteDAOImpl() {
        super(DocumentoEstudiante.class);
    }

    @Override
    public DocumentoEstudiante findByEstudianteAndTipo(Long estudianteId, Long tipoDocumentoId) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("""
                            SELECT d FROM DocumentoEstudiante d
                            WHERE d.estudiante.id = :estudianteId
                            AND d.tipoDocumento.id = :tipoDocumentoId
                            """, DocumentoEstudiante.class)
                    .setParameter("estudianteId", estudianteId)
                    .setParameter("tipoDocumentoId", tipoDocumentoId)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        } finally {
            em.close();
        }
    }

    @Override
    public List<DocumentoEstudiante> findByEstudiante(Long estudianteId) {
        EntityManager em = entityManager();
        try {
            return em.createQuery("SELECT d FROM DocumentoEstudiante d WHERE d.estudiante.id = :estudianteId", DocumentoEstudiante.class)
                    .setParameter("estudianteId", estudianteId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
