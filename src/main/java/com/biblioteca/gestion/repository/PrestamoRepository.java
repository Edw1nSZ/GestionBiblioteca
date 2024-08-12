package com.biblioteca.gestion.repository;

import com.biblioteca.gestion.datos.Libro;
import com.biblioteca.gestion.datos.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrestamoRepository extends MongoRepository<Prestamo, Long> {
    // Buscar Id usuario
    List<Prestamo> findByUsuarioId(Long usuarioId);

    // Buscar Id de libro
    List<Prestamo> findByLibroId(Long libroId);
}
