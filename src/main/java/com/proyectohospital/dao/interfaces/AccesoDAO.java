package com.proyectohospital.dao.interfaces;

import java.util.List;

import com.proyectohospital.entities.Acceso;

public interface AccesoDAO extends GenericDAO<Acceso, Long> {
    Acceso findAccesoAbierto(Long estudianteId);
    List<Acceso> findEstudiantesDentro();
    long countEstudiantesDentro();
}
