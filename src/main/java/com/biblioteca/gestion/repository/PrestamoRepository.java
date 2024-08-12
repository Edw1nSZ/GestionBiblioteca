package com.biblioteca.gestion.interfaces;

import com.biblioteca.gestion.datos.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrestamoRepository extends MongoRepository<Prestamo, Long> {
}
