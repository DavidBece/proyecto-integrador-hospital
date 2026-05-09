package com.proyectohospital.services;

import com.proyectohospital.dao.impl.DocenteDAOImpl;
import com.proyectohospital.entities.Docente;
import com.proyectohospital.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class DocenteService {

    private DocenteDAOImpl docenteDAO;

    public DocenteService() {
        this.docenteDAO = new DocenteDAOImpl();
    }

    public void registrarDocente(Docente docente) {
        docenteDAO.save(docente);
    }

    public Docente obtenerDocente(Long id) {
        return docenteDAO.findById(id);
    }

    public List<Docente> obtenerTodosDocentes() {
        return docenteDAO.findAll();
    }

    public void actualizarDocente(Docente docente) {
        docenteDAO.update(docente);
    }

    public void eliminarDocente(Long id) {
        Docente docente = docenteDAO.findById(id);
        if (docente != null) {
            docenteDAO.delete(docente);
        }
    }
}