package com.biblioteca.gestion.repository;

import com.biblioteca.gestion.datos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Prueba: buscar libros por titulo
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Prueba2: Buscar libros por autor
    List<Libro> findByAutorContainingIgnoreCase(String autor);
}
