package com.proyectohospital.services;

import java.util.List;

import com.proyectohospital.dao.interfaces.EstudianteDAO;
import com.proyectohospital.entities.Estudiante;
import com.proyectohospital.utils.BusinessException;

public class EstudianteService {
    private final EstudianteDAO estudianteDAO;

    public EstudianteService(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    public void crear(Estudiante estudiante) {
        if (estudianteDAO.findByDocumento(estudiante.getDocumento()) != null) {
            throw new BusinessException("Ya existe un estudiante con documento " + estudiante.getDocumento());
        }
        estudianteDAO.save(estudiante);
    }

    public Estudiante actualizar(Estudiante estudiante) {
        return estudianteDAO.update(estudiante);
    }

    public Estudiante buscarPorId(Long id) {
        Estudiante estudiante = estudianteDAO.findById(id);
        if (estudiante == null) {
            throw new BusinessException("No existe estudiante con id " + id);
        }
        return estudiante;
    }

    public Estudiante buscarPorDocumento(String documento) {
        return estudianteDAO.findByDocumento(documento);
    }

    public List<Estudiante> listar() {
        return estudianteDAO.findAll();
    }

    public void eliminar(Estudiante estudiante) {
        estudianteDAO.delete(estudiante);
    }
}
