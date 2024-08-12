package com.biblioteca.gestion.Service;

import com.biblioteca.gestion.datos.Libro;
import com.biblioteca.gestion.repository.LibroRepository;
import com.biblioteca.gestion.exception.LibroNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new LibroNotFoundException(id));
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> buscarLibrosPorAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor);
    }

    public Libro actualizarCopiasDisponibles(Long id, int cantidad) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException(id));
        libro.setCopiasDisponibles(cantidad);
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(Long id, Libro libroDetalles) {
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException(id));

        libroExistente.setTitulo(libroDetalles.getTitulo());
        libroExistente.setAutor(libroDetalles.getAutor());

        return libroRepository.save(libroExistente);
    }

    public void eliminarLibro(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new LibroNotFoundException(id));

        libroRepository.delete(libro);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }


}
