package com.proyectohospital.controllers;

import com.proyectohospital.entities.Horario;
import com.proyectohospital.services.HorarioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/horario")
public class HorarioServlet extends HttpServlet {

    private final HorarioService horarioService = new HorarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            List<Horario> horarios = horarioService.obtenerTodosHorarios();
            request.setAttribute("horarios", horarios);
            request.getRequestDispatcher("/horarios.jsp").forward(request, response);
        } else {
            response.sendRedirect("horarios.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Simplificar, asumir IDs
        String diaSemana = request.getParameter("diaSemana");
        LocalTime horaInicio = LocalTime.parse(request.getParameter("horaInicio"));
        LocalTime horaFin = LocalTime.parse(request.getParameter("horaFin"));
        Horario horario = new Horario(diaSemana, horaInicio, horaFin, null, null); // TODO: agregar FKs
        horarioService.asignarHorario(horario);
        response.sendRedirect("horario?action=list");
    }
}