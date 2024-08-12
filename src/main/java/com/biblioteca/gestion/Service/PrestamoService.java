package com.biblioteca.gestion.Service;

import com.biblioteca.gestion.datos.Prestamo;
import com.biblioteca.gestion.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    // Métodos CRUD
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public Prestamo obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    public Prestamo actualizarPrestamo(Long id, Prestamo prestamoDetalles) {
        Prestamo prestamoExistente = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));

        // Actualiza solo los detalles necesarios
        prestamoExistente.setFechaPrestamo(prestamoDetalles.getFechaPrestamo());
        prestamoExistente.setFechaDevolucion(prestamoDetalles.getFechaDevolucion());

        return prestamoRepository.save(prestamoExistente);
    }


    public void eliminarPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));

        prestamoRepository.delete(prestamo);
    }
}
