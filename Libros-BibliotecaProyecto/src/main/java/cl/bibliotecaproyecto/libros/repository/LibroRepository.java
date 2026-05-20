package cl.bibliotecaproyecto.libros.repository;


import cl.bibliotecaproyecto.libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByTituloLibroContainingIgnoreCase(String titulo);
    List<Libro> findByIdCategoria(Long idCategoria);
}
