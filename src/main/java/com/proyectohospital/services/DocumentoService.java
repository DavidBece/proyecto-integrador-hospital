package com.proyectohospital.services;

import java.time.LocalDateTime;
import java.util.List;

import com.proyectohospital.dao.interfaces.DocumentoEstudianteDAO;
import com.proyectohospital.dao.interfaces.TipoDocumentoDAO;
import com.proyectohospital.entities.DocumentoEstudiante;
import com.proyectohospital.entities.EstadoDocumento;
import com.proyectohospital.entities.Estudiante;
import com.proyectohospital.entities.TipoDocumento;
import com.proyectohospital.utils.BusinessException;

public class DocumentoService {
    private final TipoDocumentoDAO tipoDocumentoDAO;
    private final DocumentoEstudianteDAO documentoDAO;

    public DocumentoService(TipoDocumentoDAO tipoDocumentoDAO, DocumentoEstudianteDAO documentoDAO) {
        this.tipoDocumentoDAO = tipoDocumentoDAO;
        this.documentoDAO = documentoDAO;
    }

    public void crearTipoDocumento(TipoDocumento tipoDocumento) {
        tipoDocumentoDAO.save(tipoDocumento);
    }

    public void registrarDocumento(DocumentoEstudiante documento) {
        DocumentoEstudiante existente = documentoDAO.findByEstudianteAndTipo(
                documento.getEstudiante().getId(),
                documento.getTipoDocumento().getId()
        );
        if (existente != null) {
            throw new BusinessException("El estudiante ya tiene registrado el documento " + documento.getTipoDocumento().getNombre());
        }
        documentoDAO.save(documento);
    }

    public DocumentoEstudiante validarDocumento(Long documentoId, EstadoDocumento estado, String observacion) {
        DocumentoEstudiante documento = documentoDAO.findById(documentoId);
        if (documento == null) {
            throw new BusinessException("No existe documento con id " + documentoId);
        }
        documento.setEstado(estado);
        documento.setObservacion(observacion);
        documento.setFechaValidacion(LocalDateTime.now());
        return documentoDAO.update(documento);
    }

    public boolean tieneDocumentosObligatoriosAprobados(Estudiante estudiante) {
        List<TipoDocumento> obligatorios = tipoDocumentoDAO.findObligatorios();
        for (TipoDocumento tipo : obligatorios) {
            DocumentoEstudiante documento = documentoDAO.findByEstudianteAndTipo(estudiante.getId(), tipo.getId());
            if (documento == null || documento.getEstado() != EstadoDocumento.APROBADO) {
                return false;
            }
        }
        return true;
    }

    public List<DocumentoEstudiante> listarDocumentosEstudiante(Long estudianteId) {
        return documentoDAO.findByEstudiante(estudianteId);
    }
}
