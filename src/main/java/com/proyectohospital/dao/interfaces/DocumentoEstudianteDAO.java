package com.proyectohospital.dao.interfaces;

import java.util.List;

import com.proyectohospital.entities.DocumentoEstudiante;

public interface DocumentoEstudianteDAO extends GenericDAO<DocumentoEstudiante, Long> {
    DocumentoEstudiante findByEstudianteAndTipo(Long estudianteId, Long tipoDocumentoId);
    List<DocumentoEstudiante> findByEstudiante(Long estudianteId);
}
