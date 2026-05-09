package com.proyectohospital.dao.interfaces;

import com.proyectohospital.entities.Programacion;

public interface ProgramacionDAO extends GenericDAO<Programacion, Long> {
    Programacion findByEstudianteMesAnio(Long estudianteId, int mes, int anio);
}
