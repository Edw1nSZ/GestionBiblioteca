package com.biblioteca.gestion.Service;

import com.biblioteca.gestion.datos.Libro;
import com.biblioteca.gestion.interfaces.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    // métodos CRUD
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }


}
