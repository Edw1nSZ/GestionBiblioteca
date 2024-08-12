package com.biblioteca.gestion.controllers;

import com.biblioteca.gestion.Service.PrestamoService;
import com.biblioteca.gestion.datos.Prestamo;
import com.biblioteca.gestion.exception.PrestamoNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@Validated
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestParam Long libroId, @RequestParam Long usuarioId) {
        Prestamo prestamo = prestamoService.crearPrestamo(libroId, usuarioId);
        if (prestamo == null) {
            throw new PrestamoNotFoundException(libroId, usuarioId);
        }
        return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/devolucion")
    public ResponseEntity<Prestamo> devolverPrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamoService.devolverPrestamo(id);
        if (prestamo == null) {
            throw new PrestamoNotFoundException(id);
        }
        return ResponseEntity.ok(prestamo);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Prestamo>> buscarPrestamosPorUsuario(@PathVariable Long usuarioId) {
        List<Prestamo> prestamos = prestamoService.buscarPrestamosPorUsuario(usuarioId);
        if (prestamos.isEmpty()) {
            throw new PrestamoNotFoundException(usuarioId);
        }
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<Prestamo>> buscarPrestamosPorLibro(@PathVariable Long libroId) {
        List<Prestamo> prestamos = prestamoService.buscarPrestamosPorLibro(libroId);
        if (prestamos.isEmpty()) {
            throw new PrestamoNotFoundException(libroId);
        }
        return ResponseEntity.ok(prestamos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(
            @PathVariable Long id,
            @Valid @RequestBody Prestamo prestamoDetalles) {
        Prestamo prestamoActualizado = prestamoService.actualizarPrestamo(id, prestamoDetalles);
        if (prestamoActualizado == null) {
            throw new PrestamoNotFoundException(id);
        }
        return ResponseEntity.ok(prestamoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        try {
            prestamoService.eliminarPrestamo(id);
        } catch (PrestamoNotFoundException e) {
            throw new PrestamoNotFoundException(id);
        }
        return ResponseEntity.noContent().build();
    }
}
