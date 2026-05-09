package com.proyectohospital.controllers;

import com.proyectohospital.entities.RegistroIngreso;
import com.proyectohospital.services.RegistroIngresoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/registroIngreso")
public class RegistroIngresoServlet extends HttpServlet {

    private final RegistroIngresoService registroIngresoService = new RegistroIngresoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            List<RegistroIngreso> registros = registroIngresoService.obtenerTodosRegistros();
            request.setAttribute("registros", registros);
            request.getRequestDispatcher("/registrosIngreso.jsp").forward(request, response);
        } else {
            response.sendRedirect("registrosIngreso.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate fecha = LocalDate.parse(request.getParameter("fecha"));
        LocalTime horaEntrada = LocalTime.parse(request.getParameter("horaEntrada"));
        RegistroIngreso registro = new RegistroIngreso(fecha, horaEntrada, null, null); // TODO: agregar estudiante
        registroIngresoService.registrarIngreso(registro);
        response.sendRedirect("registroIngreso?action=list");
    }
}