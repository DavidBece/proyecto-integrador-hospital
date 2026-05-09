package com.proyectohospital.dao.interfaces;

import java.util.List;

import com.proyectohospital.entities.TipoDocumento;

public interface TipoDocumentoDAO extends GenericDAO<TipoDocumento, Long> {
    List<TipoDocumento> findObligatorios();
}
