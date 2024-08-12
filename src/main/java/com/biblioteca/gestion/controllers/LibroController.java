package com.biblioteca.gestion.controllers;

import com.biblioteca.gestion.Service.LibroService;
import com.biblioteca.gestion.datos.Libro;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@Validated
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@Valid @RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardarLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerLibroPorId(id);
        return ResponseEntity.ok(libro);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Libro>> buscarLibros(
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "autor", required = false) String autor) {
        List<Libro> libros;
        if (titulo != null) {
            libros = libroService.buscarLibrosPorTitulo(titulo);
        } else if (autor != null) {
            libros = libroService.buscarLibrosPorAutor(autor);
        } else {
            libros = libroService.obtenerTodosLosLibros();
        }
        return ResponseEntity.ok(libros);
    }

    @PutMapping("/{id}/copias")
    public ResponseEntity<Libro> actualizarCopiasDisponibles(
            @PathVariable Long id,
            @RequestParam int cantidad) {
        Libro libroActualizado = libroService.actualizarCopiasDisponibles(id, cantidad);
        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}
