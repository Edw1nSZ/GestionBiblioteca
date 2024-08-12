package com.biblioteca.gestion.exception;

public class LibroNotFoundException extends RuntimeException {
    public LibroNotFoundException(Long id) {
        super("Libro no encontrado con ID: " + id);
    }
}
