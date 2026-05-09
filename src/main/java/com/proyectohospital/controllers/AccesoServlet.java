package com.proyectohospital.controllers;

import com.proyectohospital.dao.impl.AccesoDAOImpl;
import com.proyectohospital.dao.impl.EstudianteDAOImpl;
import com.proyectohospital.entities.Acceso;
import com.proyectohospital.entities.EstadoAcceso;
import com.proyectohospital.entities.Estudiante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet("/acceso")
public class AccesoServlet extends HttpServlet {

    private final EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl();
    private final AccesoDAOImpl accesoDAO = new AccesoDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String documento = request.getParameter("documento");

        Estudiante estudiante =
                estudianteDAO.findByDocumento(documento);

        response.setContentType("text/html;charset=UTF-8");

        if (estudiante == null) {

            response.getWriter().println("""
                    <h2>Estudiante no encontrado</h2>
                    """);

            return;
        }

        Acceso accesoActivo =
accesoDAO.findAccesoAbierto(estudiante.getId());

        // SI NO HAY ACCESO ABIERTO → REGISTRAR ENTRADA
        if (accesoActivo == null) {

            Acceso nuevoAcceso = new Acceso();

            nuevoAcceso.setEstudiante(estudiante);
            nuevoAcceso.setFecha(LocalDate.now());
            nuevoAcceso.setHoraIngreso(LocalDateTime.now());
            nuevoAcceso.setEstado(EstadoAcceso.DENTRO);

            accesoDAO.save(nuevoAcceso);

            response.getWriter().println("""
                    <h2>Ingreso registrado correctamente</h2>
                    """);

        } else {

            // REGISTRAR SALIDA
            accesoActivo.setHoraSalida(LocalDateTime.now());
            accesoActivo.setEstado(EstadoAcceso.FUERA);

            accesoDAO.update(accesoActivo);

            response.getWriter().println("""
                    <h2>Salida registrada correctamente</h2>
                    """);
        }
    }
}