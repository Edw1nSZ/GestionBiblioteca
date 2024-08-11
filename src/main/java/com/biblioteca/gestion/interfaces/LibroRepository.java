package com.biblioteca.gestion.interfaces;

import com.biblioteca.gestion.datos.Libro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibroRepository extends MongoRepository<Libro, Long> {
}
