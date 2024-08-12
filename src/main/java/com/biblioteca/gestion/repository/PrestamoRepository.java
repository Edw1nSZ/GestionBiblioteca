package com.biblioteca.gestion.repository;

import com.biblioteca.gestion.datos.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
