package com.proyectohospital.services;

import com.proyectohospital.dao.impl.RegistroIngresoDAOImpl;
import com.proyectohospital.entities.RegistroIngreso;
import com.proyectohospital.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RegistroIngresoService {

    private RegistroIngresoDAOImpl registroIngresoDAO;

    public RegistroIngresoService() {
        this.registroIngresoDAO = new RegistroIngresoDAOImpl();
    }

    public void registrarIngreso(RegistroIngreso registro) {
        registroIngresoDAO.save(registro);
    }

    public RegistroIngreso obtenerRegistro(Long id) {
        return registroIngresoDAO.findById(id);
    }

    public List<RegistroIngreso> obtenerTodosRegistros() {
        return registroIngresoDAO.findAll();
    }

    public void actualizarRegistro(RegistroIngreso registro) {
        registroIngresoDAO.update(registro);
    }

    public void eliminarRegistro(Long id) {
        RegistroIngreso registro = registroIngresoDAO.findById(id);
        if (registro != null) {
            registroIngresoDAO.delete(registro);
        }
    }
}