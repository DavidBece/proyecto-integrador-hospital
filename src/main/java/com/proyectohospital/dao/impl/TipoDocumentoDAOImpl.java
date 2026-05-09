package com.proyectohospital.dao.impl;

import jakarta.persistence.EntityManager;
import java.util.List;

import com.proyectohospital.dao.interfaces.TipoDocumentoDAO;
import com.proyectohospital.entities.TipoDocumento;

public class TipoDocumentoDAOImpl extends GenericDAOImpl<TipoDocumento, Long> implements TipoDocumentoDAO {
    public TipoDocumentoDAOImpl() {
        super(TipoDocumento.class);
    }

    @Override
    public List<TipoDocumento> findObligatorios() {
        EntityManager em = entityManager();
        try {
            return em.createQuery("SELECT td FROM TipoDocumento td WHERE td.obligatorio = true", TipoDocumento.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
