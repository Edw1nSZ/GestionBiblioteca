package com.biblioteca.gestion.Service;

import com.biblioteca.gestion.datos.Libro;
import com.biblioteca.gestion.datos.Prestamo;
import com.biblioteca.gestion.datos.Usuario;
import com.biblioteca.gestion.exception.PrestamoNotFoundException;
import com.biblioteca.gestion.repository.LibroRepository;
import com.biblioteca.gestion.repository.PrestamoRepository;
import com.biblioteca.gestion.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, LibroRepository libroRepository, UsuarioRepository usuarioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Prestamo crearPrestamo(Long libroId, Long usuarioId) {
        Libro libro = libroRepository.findById(libroId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (libro.getCopiasDisponibles() <= 0) {
            throw new RuntimeException("No hay copias disponibles");
        }

        libro.setCopiasDisponibles(libro.getCopiasDisponibles() - 1);
        libroRepository.save(libro);

        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusWeeks(2)); // Ejemplo: devolución en 2 semanas

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo devolverPrestamo(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado"));

        Libro libro = prestamo.getLibro();
        libro.setCopiasDisponibles(libro.getCopiasDisponibles() + 1);
        libroRepository.save(libro);

        prestamo.setFechaDevolucion(LocalDate.now());
        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> obtenerTodosLosPrestamos() {
        return prestamoRepository.findAll();
    }

    public Prestamo obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNotFoundException(id));
    }

    public List<Prestamo> buscarPrestamosPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    public List<Prestamo> buscarPrestamosPorLibro(Long libroId) {
        return prestamoRepository.findByLibroId(libroId);
    }

    public Prestamo actualizarPrestamo(Long id, Prestamo prestamoDetalles) {
        Prestamo prestamoExistente = prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNotFoundException(id));

        prestamoExistente.setFechaDevolucion(prestamoDetalles.getFechaDevolucion());
        return prestamoRepository.save(prestamoExistente);
    }

    public void eliminarPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNotFoundException(id));

        prestamoRepository.delete(prestamo);
    }

}
