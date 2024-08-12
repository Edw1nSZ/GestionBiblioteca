package com.biblioteca.gestion.repository;

import com.biblioteca.gestion.datos.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LibroRepository extends MongoRepository<Libro, Long> {

    // Prueba: buscar libros por titulo
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Prueba2: Buscar libros por autor
    List<Libro> findByAutorContainingIgnoreCase(String autor);
}
