package com.biblioteca.gestion.repository;

import com.biblioteca.gestion.datos.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {// Buscar Id usuario
    List<Prestamo> findByUsuarioId(Long usuarioId);

    // Buscar Id de libro
    List<Prestamo> findByLibroId(Long libroId);
}
