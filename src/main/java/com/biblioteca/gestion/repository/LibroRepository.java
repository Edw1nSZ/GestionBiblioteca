package com.biblioteca.gestion.repository;

import com.biblioteca.gestion.datos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

}
