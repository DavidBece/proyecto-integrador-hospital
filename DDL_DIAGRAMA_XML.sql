DROP DATABASE IF EXISTS PracticasHUSRT;
CREATE DATABASE IF NOT EXISTS PracticasHUSRT;
USE PracticasHUSRT;

-- =====================================================================================
-- TABLAS MAESTRAS
-- =====================================================================================

CREATE TABLE IF NOT EXISTS universidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    ciudad VARCHAR(100),
    estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS especialidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS docente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    especialidad VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS servicio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    id_area_medica BIGINT,
    FOREIGN KEY (id_area_medica) REFERENCES especialidad(id)
);

CREATE TABLE IF NOT EXISTS tutor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    tipo ENUM('UNIVERSIDAD', 'HOSPITAL') NOT NULL,
    universidad_id BIGINT,
    FOREIGN KEY (universidad_id) REFERENCES universidad(id)
);

-- =====================================================================================
-- ESTUDIANTE (modelo del XML: id_estudiante + tablas 1:1 de datos)
-- =====================================================================================

CREATE TABLE IF NOT EXISTS estudiante (
    id_estudiante BIGINT AUTO_INCREMENT PRIMARY KEY,

    universidad_id BIGINT,

    induccion_completa BOOLEAN DEFAULT FALSE,
    vacunas_completas BOOLEAN DEFAULT FALSE,
    activo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (universidad_id) REFERENCES universidad(id)
);

-- 1:1 Datos Personales
CREATE TABLE IF NOT EXISTS datos_personales (
    id_datos_personales BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL UNIQUE,

    nombres VARCHAR(150) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    documento VARCHAR(50) NOT NULL UNIQUE,
    estado_civil VARCHAR(50),
    fecha_nacimiento DATE,
    lugar_nacimiento VARCHAR(100),

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante)
);

-- 1:1 Contacto
CREATE TABLE IF NOT EXISTS contacto (
    id_contacto BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL UNIQUE,

    direccion_tunja VARCHAR(200),
    residencia_permanente VARCHAR(200),
    celular VARCHAR(20),
    correo VARCHAR(150),

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante)
);

-- 1:1 Representante Legal
CREATE TABLE IF NOT EXISTS representante_legal (
    id_representante_legal BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL UNIQUE,

    nombre_representante VARCHAR(150),
    parentesco VARCHAR(50),
    celular_representante VARCHAR(20),
    direccion_representante VARCHAR(200),
    ciudad_representante VARCHAR(100)
);

ALTER TABLE representante_legal
    ADD CONSTRAINT fk_repr_est
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante);

-- 1:1 Información Personal Adicional
CREATE TABLE IF NOT EXISTS informacion_personal_adicional (
    id_info_personal_adicional BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL UNIQUE,

    idioma_adicional VARCHAR(100),
    actividades_complementarias TEXT
);

ALTER TABLE informacion_personal_adicional
    ADD CONSTRAINT fk_info_personal_adicional_est
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante);

-- 1:1 Convivencia
CREATE TABLE IF NOT EXISTS convivencia (
    id_convivencia BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL UNIQUE,

    companeros_vivienda TEXT,
    nucleo_familiar TEXT
);

ALTER TABLE convivencia
    ADD CONSTRAINT fk_convivencia_est
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante);

-- 1:1 Información Familiar
CREATE TABLE IF NOT EXISTS informacion_familiar (
    id_informacion_familiar BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL UNIQUE,

    nombre_padre VARCHAR(150),
    edad_padre INT,
    nombre_madre VARCHAR(150),
    edad_madre INT,

    tiene_hijos BOOLEAN,
    nombre_hijos TEXT,
    edades_hijos TEXT,

    tiene_conyuge BOOLEAN,
    nombre_conyuge VARCHAR(150),
    edad_conyuge INT
);

ALTER TABLE informacion_familiar
    ADD CONSTRAINT fk_informacion_familiar_est
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante);

-- 1:1 Salud
CREATE TABLE IF NOT EXISTS salud (
    id_salud BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL UNIQUE,

    enfermedades_generales TEXT,
    enfermedades_mentales TEXT,
    medicamentos TEXT,
    alergias TEXT,
    peso_talla_imc VARCHAR(50),
    grupo_sanguineo VARCHAR(10)
);

ALTER TABLE salud
    ADD CONSTRAINT fk_salud_est
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante);

-- 1:1 Datos Académicos
CREATE TABLE IF NOT EXISTS datos_academicos (
    id_datos_academicos BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL UNIQUE,

    programa VARCHAR(150),
    fecha_ingreso DATE,
    semestre INT,
    promedio DECIMAL(4,2),
    investigacion TEXT
);

ALTER TABLE datos_academicos
    ADD CONSTRAINT fk_datos_academicos_est
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante);

-- =====================================================================================
-- PROGRAMACIONES / DETALLE / HORARIOS
-- (modelo del XML: id_programacion + id_programacion_detalle)
-- =====================================================================================

CREATE TABLE IF NOT EXISTS programacion (
    id_programacion BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,

    mes INT NOT NULL,
    anio INT NOT NULL,

    id_docente BIGINT,

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante),
    FOREIGN KEY (id_docente) REFERENCES docente(id),

    UNIQUE (estudiante_id, mes, anio)
);

CREATE TABLE IF NOT EXISTS programacion_detalle (
    id_programacion_detalle BIGINT AUTO_INCREMENT PRIMARY KEY,
    programacion_id BIGINT NOT NULL,

    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,

    especialidad_id BIGINT NOT NULL,

    FOREIGN KEY (programacion_id) REFERENCES programacion(id_programacion),
    FOREIGN KEY (especialidad_id) REFERENCES especialidad(id)
);

CREATE TABLE IF NOT EXISTS horario (
    id_horario BIGINT AUTO_INCREMENT PRIMARY KEY,
    dia_semana VARCHAR(20) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,

    id_practica BIGINT,
    id_servicio BIGINT,

    FOREIGN KEY (id_practica) REFERENCES programacion(id_programacion),
    FOREIGN KEY (id_servicio) REFERENCES servicio(id)
);

CREATE TABLE IF NOT EXISTS capacidad_practica (
    id_capacidad_practica BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_especialidad BIGINT NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,

    capacidad_maxima INT NOT NULL,
    activa BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id),
    UNIQUE (id_especialidad, hora_inicio, hora_fin)
);

-- =====================================================================================
-- ACCESO / REGISTRO INGRESO
-- =====================================================================================

CREATE TABLE IF NOT EXISTS acceso (
    id_acceso BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,

    fecha DATE NOT NULL,
    hora_ingreso DATETIME,
    hora_salida DATETIME,

    estado ENUM('DENTRO', 'FUERA') DEFAULT 'FUERA',

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante)
);

CREATE TABLE IF NOT EXISTS registro_ingreso (
    id_registro BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora_entrada TIME NOT NULL,
    hora_salida TIME,
    id_estudiante BIGINT NOT NULL,

    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id_estudiante)
);

-- =====================================================================================
-- DOCUMENTOS / VACUNAS
-- =====================================================================================

CREATE TABLE IF NOT EXISTS tipo_documento (
    id_tipo_documento BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    obligatorio BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS documento_estudiante (
    id_documento_estudiante BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    tipo_documento_id BIGINT NOT NULL,

    nombre_archivo VARCHAR(255),
    ruta_archivo TEXT,

    estado ENUM('PENDIENTE', 'APROBADO', 'RECHAZADO') DEFAULT 'PENDIENTE',
    fecha_carga DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_validacion DATETIME,

    observacion TEXT,

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante),
    FOREIGN KEY (tipo_documento_id) REFERENCES tipo_documento(id_tipo_documento),

    UNIQUE (estudiante_id, tipo_documento_id)
);

CREATE TABLE IF NOT EXISTS vacuna (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    dosis_requeridas INT,
    requiere_refuerzo BOOLEAN
);

CREATE TABLE IF NOT EXISTS estudiante_vacuna (
    id_estudiante_vacuna BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    vacuna_id BIGINT NOT NULL,

    dosis_aplicadas INT,
    fecha_ultima_dosis DATE,

    soporte_documento_id BIGINT,

    estado ENUM('INCOMPLETA', 'COMPLETA', 'PENDIENTE_VALIDACION') NOT NULL,

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id_estudiante),
    FOREIGN KEY (vacuna_id) REFERENCES vacuna(id),
    FOREIGN KEY (soporte_documento_id) REFERENCES documento_estudiante(id_documento_estudiante),

    UNIQUE (estudiante_id, vacuna_id)
);

-- =====================================================================================
-- TRIGGERS (ACID)
-- =====================================================================================

DROP TRIGGER IF EXISTS validar_solapamiento;
DELIMITER $$
CREATE TRIGGER validar_solapamiento
BEFORE INSERT ON programacion_detalle
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT 1
        FROM programacion_detalle pd
        JOIN programacion p_existente ON p_existente.id_programacion = pd.programacion_id
        JOIN programacion p_nueva ON p_nueva.id_programacion = NEW.programacion_id
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

-- =====================================================================================
-- Nota
-- Este DDL se hizo para que sea consistente con los nombres/PK/FK que ya existen en tus 
-- entidades nuevas (DatosPersonales/Contacto/RepresentanteLegal/etc.) y con PKs:
-- id_estudiante, id_programacion, id_programacion_detalle, id_horario, id_documento_estudiante,
-- id_estudiante_vacuna, etc.
-- =====================================================================================

