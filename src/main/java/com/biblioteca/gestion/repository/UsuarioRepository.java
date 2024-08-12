package com.biblioteca.gestion.interfaces;

import com.biblioteca.gestion.datos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}