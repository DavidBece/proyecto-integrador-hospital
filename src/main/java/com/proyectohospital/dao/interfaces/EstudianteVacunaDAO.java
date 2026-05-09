package com.proyectohospital.dao.interfaces;

import java.util.List;

import com.proyectohospital.entities.EstudianteVacuna;

public interface EstudianteVacunaDAO extends GenericDAO<EstudianteVacuna, Long> {
    EstudianteVacuna findByEstudianteAndVacuna(Long estudianteId, Long vacunaId);
    List<EstudianteVacuna> findByEstudiante(Long estudianteId);
}
