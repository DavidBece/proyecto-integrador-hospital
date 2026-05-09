package com.proyectohospital.dao.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.proyectohospital.entities.ProgramacionDetalle;

public interface ProgramacionDetalleDAO extends GenericDAO<ProgramacionDetalle, Long> {
    List<ProgramacionDetalle> findOverlapping(Long estudianteId, LocalDate fecha, LocalTime inicio, LocalTime fin);
    List<ProgramacionDetalle> findByHorario(Long estudianteId, LocalDate fecha, LocalTime hora);
    long countByEspecialidadFechaHorario(Long especialidadId, LocalDate fecha, LocalTime inicio, LocalTime fin);
}
