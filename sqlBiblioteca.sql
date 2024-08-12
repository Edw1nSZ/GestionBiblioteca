drop database biblioteca_db;

create database biblioteca_db;
use biblioteca_db;

-- tabla de Usuarios
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    nombre VARCHAR(255),
    password VARCHAR(255)
);
INSERT INTO usuario (email, nombre, password) VALUES ('test@example.com', 'Test User', 'password123');

-- tabla de Libros
drop table libros;
CREATE TABLE libros (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    autor VARCHAR(255),
    copiasDisponibles INT
);
ALTER TABLE libros MODIFY copiasDisponibles INT NULL;
INSERT INTO libros (titulo, autor, copiasDisponibles)
VALUES ('Título del Libro 1', 'Autor del Libro 1', 100);

-- tabla de Préstamos
CREATE TABLE prestamos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    libro_id BIGINT,
    usuario_id BIGINT,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE,
    FOREIGN KEY (libro_id) REFERENCES libros(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
INSERT INTO prestamos (libro_id, usuario_id, fecha_prestamo, fecha_devolucion)
VALUES (1, 1, '2024-08-10', '2024-08-20');




select * from usuario;
select * from libros;
select * from prestamos;
