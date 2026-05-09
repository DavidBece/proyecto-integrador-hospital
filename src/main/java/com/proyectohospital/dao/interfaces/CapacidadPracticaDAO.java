package com.proyectohospital.dao.interfaces;

import java.time.LocalTime;

import com.proyectohospital.entities.CapacidadPractica;

public interface CapacidadPracticaDAO extends GenericDAO<CapacidadPractica, Long> {
    CapacidadPractica findActivaByEspecialidadAndHorario(Long especialidadId, LocalTime inicio, LocalTime fin);
}
