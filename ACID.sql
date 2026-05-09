-- Reglas ACID y consultas de control para PracticasHUSRT.
-- Compatible con las tablas definidas en DDL.sql.

USE PracticasHUSRT;

-- 1. Evitar doble programacion en horarios solapados para el mismo estudiante.
-- Ajustado para modelo del diagrama XML (PKs: id_programacion, id_programacion_detalle, etc.).

DROP TRIGGER IF EXISTS validar_solapamiento;

DELIMITER $$

CREATE TRIGGER validar_solapamiento
BEFORE INSERT ON programacion_detalle
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1
        FROM programacion_detalle pd
        JOIN programacion p_existente ON p_existente.id = pd.programacion_id
        JOIN programacion p_nueva ON p_nueva.id = NEW.programacion_id
        WHERE p_existente.estudiante_id = p_nueva.estudiante_id
          AND pd.fecha = NEW.fecha
          AND NEW.hora_inicio < pd.hora_fin
          AND NEW.hora_fin > pd.hora_inicio
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El estudiante ya tiene una programacion en ese horario';
    END IF;
END$$

DELIMITER ;

-- 2. Consulta para validar que un estudiante tenga programacion en un horario.
-- Reemplaza los valores de ejemplo por los datos reales del ingreso.
SELECT pd.*
FROM programacion_detalle pd
JOIN programacion p ON p.id = pd.programacion_id
WHERE p.estudiante_id = 1
  AND pd.fecha = CURDATE()
  AND CURTIME() BETWEEN pd.hora_inicio AND pd.hora_fin;

-- 3. Consulta para validar requisitos basicos del estudiante antes del ingreso.
SELECT e.*
FROM estudiante e
WHERE e.id = 1
  AND e.induccion_completa = TRUE
  AND e.vacunas_completas = TRUE
  AND e.activo = TRUE;

-- 4. Consulta para validar documentos obligatorios aprobados.
SELECT td.id, td.nombre
FROM tipo_documento td
LEFT JOIN documento_estudiante de
       ON de.tipo_documento_id = td.id
      AND de.estudiante_id = 1
      AND de.estado = 'APROBADO'
WHERE td.obligatorio = TRUE
  AND de.id IS NULL;

-- Si esta consulta no retorna filas, el estudiante tiene todos los documentos
-- obligatorios aprobados.

-- 5. Consulta para validar vacunas registradas como completas.
SELECT ev.*
FROM estudiante_vacuna ev
WHERE ev.estudiante_id = 1
  AND ev.estado <> 'COMPLETA';

-- Si esta consulta no retorna filas y estudiante.vacunas_completas = TRUE,
-- el estudiante cumple el requisito de vacunas.

-- 6. Estudiantes actualmente dentro del hospital.
SELECT e.nombres, e.apellidos
FROM acceso a
JOIN estudiante e ON e.id = a.estudiante_id
WHERE a.estado = 'DENTRO';

-- 7. Numero de estudiantes dentro del hospital.
SELECT COUNT(*) AS estudiantes_dentro
FROM acceso
WHERE estado = 'DENTRO';
