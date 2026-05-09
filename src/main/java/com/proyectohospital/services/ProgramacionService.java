package com.proyectohospital.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.proyectohospital.dao.interfaces.CapacidadPracticaDAO;
import com.proyectohospital.dao.interfaces.ProgramacionDAO;
import com.proyectohospital.dao.interfaces.ProgramacionDetalleDAO;
import com.proyectohospital.entities.CapacidadPractica;
import com.proyectohospital.entities.Programacion;
import com.proyectohospital.entities.ProgramacionDetalle;
import com.proyectohospital.utils.BusinessException;

public class ProgramacionService {
    private final ProgramacionDAO programacionDAO;
    private final ProgramacionDetalleDAO detalleDAO;
    private final CapacidadPracticaDAO capacidadDAO;

    public ProgramacionService(ProgramacionDAO programacionDAO, ProgramacionDetalleDAO detalleDAO, CapacidadPracticaDAO capacidadDAO) {
        this.programacionDAO = programacionDAO;
        this.detalleDAO = detalleDAO;
        this.capacidadDAO = capacidadDAO;
    }

    public void crearProgramacion(Programacion programacion) {
        Programacion existente = programacionDAO.findByEstudianteMesAnio(
                programacion.getEstudiante().getId(),
                programacion.getMes(),
                programacion.getAnio()
        );
        if (existente != null) {
            throw new BusinessException("El estudiante ya tiene programación para ese mes y año");
        }
        if (programacion.getDocente() == null) {
            throw new BusinessException("La programación requiere un docente asignado");
        }
        programacionDAO.save(programacion);
    }

    public void agregarBloque(ProgramacionDetalle detalle) {
        if (!detalle.getHoraInicio().isBefore(detalle.getHoraFin())) {
            throw new BusinessException("La hora de inicio debe ser menor a la hora de fin");
        }
        Long estudianteId = detalle.getProgramacion().getEstudiante().getId();
        List<ProgramacionDetalle> solapados = detalleDAO.findOverlapping(
                estudianteId,
                detalle.getFecha(),
                detalle.getHoraInicio(),
                detalle.getHoraFin()
        );
        if (!solapados.isEmpty()) {
            throw new BusinessException("El estudiante ya tiene una programación en ese horario");
        }
        validarCapacidad(detalle);
        detalleDAO.save(detalle);
    }

    public boolean tieneProgramacionEnHorario(Long estudianteId, LocalDate fecha, LocalTime hora) {
        return !detalleDAO.findByHorario(estudianteId, fecha, hora).isEmpty();
    }

    private void validarCapacidad(ProgramacionDetalle detalle) {
        CapacidadPractica capacidad = capacidadDAO.findActivaByEspecialidadAndHorario(
                detalle.getEspecialidad().getId(),
                detalle.getHoraInicio(),
                detalle.getHoraFin()
        );
        if (capacidad == null) {
            return;
        }
        long asignados = detalleDAO.countByEspecialidadFechaHorario(
                detalle.getEspecialidad().getId(),
                detalle.getFecha(),
                detalle.getHoraInicio(),
                detalle.getHoraFin()
        );
        if (asignados >= capacidad.getCapacidadMaxima()) {
            throw new BusinessException("La capacidad maxima para "
                    + detalle.getEspecialidad().getNombre()
                    + " en ese horario ya fue alcanzada");
        }
    }

    public void asignarDocente(Programacion programacion, Long docenteId) {
        // Lógica para asignar docente
        // programacion.setDocente(docente);
        // programacionDAO.update(programacion);
    }

    public void actualizarProgramacion(Programacion programacion) {
        programacionDAO.update(programacion);
    }

    public void eliminarProgramacion(Long id) {
        Programacion programacion = programacionDAO.findById(id);
        if (programacion != null) {
            programacionDAO.delete(programacion);
        }
    }
}
