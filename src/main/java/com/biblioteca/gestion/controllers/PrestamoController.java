package com.biblioteca.gestion.controllers;

import com.biblioteca.gestion.Service.PrestamoService;
import com.biblioteca.gestion.datos.Prestamo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {
    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(@RequestParam Long libroId, @RequestParam Long usuarioId) {
        Prestamo prestamo = prestamoService.crearPrestamo(libroId, usuarioId);
        return new ResponseEntity<>(prestamo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/devolucion")
    public ResponseEntity<Prestamo> devolverPrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamoService.devolverPrestamo(id);
        return ResponseEntity.ok(prestamo);
    }

    // Otros endpoints como obtener todos los préstamos, etc.
}
