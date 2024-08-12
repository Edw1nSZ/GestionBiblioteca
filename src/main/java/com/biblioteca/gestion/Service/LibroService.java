package com.biblioteca.gestion.Service;

import com.biblioteca.gestion.datos.Libro;
import com.biblioteca.gestion.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    //Metodos CRUD
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    public Libro actualizarLibro(Long id, Libro libroDetalles) {
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

        libroExistente.setTitulo(libroDetalles.getTitulo());
        libroExistente.setAutor(libroDetalles.getAutor());
        libroExistente.setCopiasDisponibles(libroDetalles.getCopiasDisponibles());

        return libroRepository.save(libroExistente);
    }

    public void eliminarLibro(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

        libroRepository.delete(libro);
    }
}