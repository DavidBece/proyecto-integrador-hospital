package com.proyectohospital.controllers;

import com.proyectohospital.entities.Docente;
import com.proyectohospital.services.DocenteService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/docente")
public class DocenteServlet extends HttpServlet {

    private final DocenteService docenteService = new DocenteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            List<Docente> docentes = docenteService.obtenerTodosDocentes();
            request.setAttribute("docentes", docentes);
            request.getRequestDispatcher("/docentes.jsp").forward(request, response);
        } else {
            response.sendRedirect("docentes.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            String nombre = request.getParameter("nombre");
            String especialidad = request.getParameter("especialidad");
            Docente docente = new Docente(nombre, especialidad);
            docenteService.registrarDocente(docente);
            response.sendRedirect("docente?action=list");
        }
    }
}