-- REEMPLAZADO: DDL.sql ahora coincide con el modelo del diagrama XML y con las entidades JPA agregadas.
-- Archivo fuente: DDL_DIAGRAMA_XML.sql
DROP DATABASE IF EXISTS PracticasHUSRT;
CREATE DATABASE IF NOT EXISTS PracticasHUSRT;
USE PracticasHUSRT;


CREATE TABLE IF NOT EXISTS universidad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    ciudad VARCHAR(100),
    estado BOOLEAN DEFAULT TRUE
);

CREATE TABLE  IF NOT EXISTS especialidad (
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

CREATE TABLE  IF NOT EXISTS tutor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    tipo ENUM('UNIVERSIDAD', 'HOSPITAL') NOT NULL,
    universidad_id BIGINT,
    FOREIGN KEY (universidad_id) REFERENCES universidad(id)
);

CREATE TABLE  IF NOT EXISTS estudiante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    -- básicos
    nombres VARCHAR(150) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    documento VARCHAR(50) NOT NULL UNIQUE,
    estado_civil VARCHAR(50),
    fecha_nacimiento DATE,
    lugar_nacimiento VARCHAR(100),

    -- contacto
    direccion_tunja VARCHAR(200),
    residencia_permanente VARCHAR(200),
    celular VARCHAR(20),
    correo VARCHAR(150),

    -- representante
    direccion_representante VARCHAR(200),
    ciudad_representante VARCHAR(100),
    nombre_representante VARCHAR(150),
    parentesco VARCHAR(50),
    celular_representante VARCHAR(20),

    -- personales
    idioma VARCHAR(100),
    actividades TEXT,

    -- familia
    nombre_padre VARCHAR(150),
    edad_padre INT,
    nombre_madre VARCHAR(150),
    edad_madre INT,

    tiene_hijos BOOLEAN,
    nombre_hijos TEXT,
    edades_hijos TEXT,

    tiene_conyuge BOOLEAN,
    nombre_conyuge VARCHAR(150),
    edad_conyuge INT,

    -- salud
    enfermedades_generales TEXT,
    enfermedades_mentales TEXT,
    medicamentos TEXT,
    alergias TEXT,
    peso_talla_imc VARCHAR(50),
    grupo_sanguineo VARCHAR(10),

    -- convivencia
    companeros_vivienda TEXT,
    nucleo_familiar TEXT,

    -- académicos
    programa VARCHAR(150),
    fecha_ingreso DATE,
    semestre INT,
    universidad_id BIGINT,
    promedio DECIMAL(4,2),
    investigacion TEXT,

    -- estado habilitación
    induccion_completa BOOLEAN DEFAULT FALSE,
    vacunas_completas BOOLEAN DEFAULT FALSE,
    activo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (universidad_id) REFERENCES universidad(id)
);

CREATE TABLE  IF NOT EXISTS programacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    mes INT NOT NULL,
    anio INT NOT NULL,

    id_docente BIGINT,

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id),
    FOREIGN KEY (id_docente) REFERENCES docente(id),

    UNIQUE (estudiante_id, mes, anio)
);

CREATE TABLE  IF NOT EXISTS programacion_detalle (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    programacion_id BIGINT NOT NULL,

    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,

    especialidad_id BIGINT NOT NULL,

    FOREIGN KEY (programacion_id) REFERENCES programacion(id),
    FOREIGN KEY (especialidad_id) REFERENCES especialidad(id)
);

CREATE TABLE IF NOT EXISTS horario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dia_semana VARCHAR(20) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    id_practica BIGINT,
    id_servicio BIGINT,
    FOREIGN KEY (id_practica) REFERENCES programacion(id),
    FOREIGN KEY (id_servicio) REFERENCES servicio(id)
);

CREATE TABLE IF NOT EXISTS capacidad_practica (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    especialidad_id BIGINT NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    capacidad_maxima INT NOT NULL,
    activa BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (especialidad_id) REFERENCES especialidad(id),
    UNIQUE(especialidad_id, hora_inicio, hora_fin)
);

CREATE TABLE IF NOT EXISTS acceso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,

    fecha DATE NOT NULL,
    hora_ingreso DATETIME,
    hora_salida DATETIME,

    estado ENUM('DENTRO', 'FUERA') DEFAULT 'FUERA',

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id)
);

CREATE TABLE IF NOT EXISTS tipo_documento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    obligatorio BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS documento_estudiante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    tipo_documento_id BIGINT NOT NULL,

    nombre_archivo VARCHAR(255),
    ruta_archivo TEXT, -- o URL

    estado ENUM('PENDIENTE', 'APROBADO', 'RECHAZADO') DEFAULT 'PENDIENTE',
    fecha_carga DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_validacion DATETIME,

    observacion TEXT,

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id),
    FOREIGN KEY (tipo_documento_id) REFERENCES tipo_documento(id),

    UNIQUE(estudiante_id, tipo_documento_id)
);

CREATE TABLE IF NOT EXISTS vacuna (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    dosis_requeridas INT,
    requiere_refuerzo BOOLEAN
);

CREATE TABLE IF NOT EXISTS estudiante_vacuna (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id BIGINT NOT NULL,
    vacuna_id BIGINT NOT NULL,

    dosis_aplicadas INT,
    fecha_ultima_dosis DATE,

    soporte_documento_id BIGINT, -- referencia al PDF

    estado ENUM('INCOMPLETA', 'COMPLETA', 'PENDIENTE_VALIDACION'),

    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id),
    FOREIGN KEY (vacuna_id) REFERENCES vacuna(id),
    FOREIGN KEY (soporte_documento_id) REFERENCES documento_estudiante(id),

    UNIQUE(estudiante_id, vacuna_id)
);

CREATE TABLE IF NOT EXISTS registro_ingreso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora_entrada TIME NOT NULL,
    hora_salida TIME,
    id_estudiante BIGINT NOT NULL,
    FOREIGN KEY (id_estudiante) REFERENCES estudiante(id)
);
