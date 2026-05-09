package com.proyectohospital.dao.interfaces;

import com.proyectohospital.entities.Estudiante;

public interface EstudianteDAO extends GenericDAO<Estudiante, Long> {
    Estudiante findByDocumento(String documento);
}
