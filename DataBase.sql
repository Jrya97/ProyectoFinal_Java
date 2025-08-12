-- Crear la base de datos
drop database veterinaria_db;
CREATE DATABASE IF NOT EXISTS veterinaria_db;
USE veterinaria_db;

-- Tabla de Propietarios
CREATE TABLE IF NOT EXISTS propietarios (
    id_propietario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    direccion VARCHAR(200),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Mascotas
CREATE TABLE IF NOT EXISTS mascotas (
    id_mascota INT AUTO_INCREMENT PRIMARY KEY,
    id_propietario INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raza VARCHAR(100),
    fecha_nacimiento DATE,
    peso DECIMAL(5,2),
    sexo ENUM('Macho', 'Hembra'),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_propietario) REFERENCES propietarios(id_propietario) ON DELETE CASCADE
);

-- Tabla de Consultas
CREATE TABLE IF NOT EXISTS consultas (
    id_consulta INT AUTO_INCREMENT PRIMARY KEY,
    id_mascota INT NOT NULL,
    fecha_consulta DATETIME NOT NULL,
    motivo VARCHAR(200) NOT NULL,
    diagnostico TEXT,
    tratamiento TEXT,
    observaciones TEXT,
    costo DECIMAL(10,2),
    FOREIGN KEY (id_mascota) REFERENCES mascotas(id_mascota) ON DELETE CASCADE
);

-- Insertar datos mplo para propietarios
INSERT INTO propietarios (nombre, apellido, telefono, email, direccion) VALUES
('Juan', 'Pérez', '555-123-4567', 'juan.perez@email.com', 'Calle Principal 123'),
('María', 'González', '555-987-6543', 'maria.gonzalez@email.com', 'Avenida Central 456'),
('Carlos', 'Rodríguez', '555-456-7890', 'carlos.rodriguez@email.com', 'Plaza Mayor 789');

-- Insertar datos  para mascotas
INSERT INTO mascotas (id_propietario, nombre, especie, raza, fecha_nacimiento, peso, sexo) VALUES
(1, 'Max', 'Perro', 'Labrador', '2019-05-15', 25.5, 'Macho'),
(1, 'Luna', 'Gato', 'Siamés', '2020-02-10', 4.2, 'Hembra'),
(2, 'Rocky', 'Perro', 'Pastor Alemán', '2018-11-20', 30.0, 'Macho'),
(3, 'Mimi', 'Gato', 'Persa', '2021-01-05', 3.8, 'Hembra');

-- Insertar datos de consultas
INSERT INTO consultas (id_mascota, fecha_consulta, motivo, diagnostico, tratamiento, observaciones, costo) VALUES
(1, '2023-01-10 10:30:00', 'Vacunación anual', 'Mascota saludable', 'Vacuna polivalente', 'Próxima revisión en un año', 50.00),
(2, '2023-02-15 15:45:00', 'Vómitos y diarrea', 'Gastroenteritis', 'Dieta blanda y antibióticos', 'Revisión en una semana', 75.50),
(3, '2023-03-20 09:15:00', 'Cojera pata trasera', 'Esguince leve', 'Reposo y antiinflamatorios', 'Mejoría notable', 60.25),
(4, '2023-04-05 14:00:00', 'Control rutinario', 'Condición óptima', 'Ninguno', 'Continuar con alimentación actual', 40.00);