package com.proyectohospital.services;

import java.util.List;

import com.proyectohospital.dao.interfaces.EstudianteDAO;
import com.proyectohospital.dao.interfaces.EstudianteVacunaDAO;
import com.proyectohospital.dao.interfaces.VacunaDAO;
import com.proyectohospital.entities.EstadoVacuna;
import com.proyectohospital.entities.Estudiante;
import com.proyectohospital.entities.EstudianteVacuna;
import com.proyectohospital.entities.Vacuna;
import com.proyectohospital.utils.BusinessException;

public class VacunaService {
    private final VacunaDAO vacunaDAO;
    private final EstudianteVacunaDAO estudianteVacunaDAO;
    private final EstudianteDAO estudianteDAO;

    public VacunaService(VacunaDAO vacunaDAO, EstudianteVacunaDAO estudianteVacunaDAO, EstudianteDAO estudianteDAO) {
        this.vacunaDAO = vacunaDAO;
        this.estudianteVacunaDAO = estudianteVacunaDAO;
        this.estudianteDAO = estudianteDAO;
    }

    public void crearVacuna(Vacuna vacuna) {
        vacunaDAO.save(vacuna);
    }

    public void registrarVacunaEstudiante(EstudianteVacuna estudianteVacuna) {
        EstudianteVacuna existente = estudianteVacunaDAO.findByEstudianteAndVacuna(
                estudianteVacuna.getEstudiante().getId(),
                estudianteVacuna.getVacuna().getId()
        );
        if (existente != null) {
            throw new BusinessException("La vacuna ya está registrada para el estudiante");
        }
        estudianteVacunaDAO.save(estudianteVacuna);
        recalcularEstadoVacunas(estudianteVacuna.getEstudiante().getId());
    }

    public boolean tieneVacunasCompletas(Estudiante estudiante) {
        List<EstudianteVacuna> registros = estudianteVacunaDAO.findByEstudiante(estudiante.getId());
        return Boolean.TRUE.equals(estudiante.getVacunasCompletas())
                && !registros.isEmpty()
                && registros.stream().allMatch(registro -> registro.getEstado() == EstadoVacuna.COMPLETA);
    }

    public void recalcularEstadoVacunas(Long estudianteId) {
        Estudiante estudiante = estudianteDAO.findById(estudianteId);
        if (estudiante == null) {
            throw new BusinessException("No existe estudiante con id " + estudianteId);
        }
        List<EstudianteVacuna> registros = estudianteVacunaDAO.findByEstudiante(estudianteId);
        boolean completas = !registros.isEmpty()
                && registros.stream().allMatch(registro -> registro.getEstado() == EstadoVacuna.COMPLETA);
        estudiante.setVacunasCompletas(completas);
        estudianteDAO.update(estudiante);
    }

    public List<EstudianteVacuna> listarVacunasEstudiante(Long estudianteId) {
        return estudianteVacunaDAO.findByEstudiante(estudianteId);
    }
}
