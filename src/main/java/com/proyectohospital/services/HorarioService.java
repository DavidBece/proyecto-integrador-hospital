package com.proyectohospital.services;

import com.proyectohospital.dao.impl.HorarioDAOImpl;
import com.proyectohospital.entities.Horario;
import com.proyectohospital.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HorarioService {

    private HorarioDAOImpl horarioDAO;

    public HorarioService() {
        this.horarioDAO = new HorarioDAOImpl();
    }

    public void asignarHorario(Horario horario) {
        horarioDAO.save(horario);
    }

    public Horario obtenerHorario(Long id) {
        return horarioDAO.findById(id);
    }

    public List<Horario> obtenerTodosHorarios() {
        return horarioDAO.findAll();
    }

    public void actualizarHorario(Horario horario) {
        horarioDAO.update(horario);
    }
}