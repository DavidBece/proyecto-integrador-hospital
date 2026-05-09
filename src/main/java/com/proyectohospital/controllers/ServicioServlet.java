package com.proyectohospital.controllers;

import com.proyectohospital.entities.Servicio;
import com.proyectohospital.services.ServicioService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/servicio")
public class ServicioServlet extends HttpServlet {

    private final ServicioService servicioService = new ServicioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            List<Servicio> servicios = servicioService.obtenerTodosServicios();
            request.setAttribute("servicios", servicios);
            request.getRequestDispatcher("/servicios.jsp").forward(request, response);
        } else {
            response.sendRedirect("servicios.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            String nombre = request.getParameter("nombre");
            // Asumir especialidad por ID, simplificar
            Servicio servicio = new Servicio(nombre, null); // TODO: agregar especialidad
            servicioService.crearServicio(servicio);
            response.sendRedirect("servicio?action=list");
        }
    }
}