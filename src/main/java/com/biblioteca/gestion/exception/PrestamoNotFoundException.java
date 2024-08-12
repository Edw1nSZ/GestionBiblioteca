package com.biblioteca.gestion.exception;

public class PrestamoNotFoundException extends RuntimeException {
    public PrestamoNotFoundException(Long libroId, Long usuarioId) {
        super("No se encontró el préstamo con Libro ID: " + libroId + " y Usuario ID: " + usuarioId);
    }
    public PrestamoNotFoundException(Long id) {
        super("Préstamo no encontrado con ID: " + id);
    }
}
