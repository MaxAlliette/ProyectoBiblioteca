package cl.bibliotecaproyecto.autores.repository;

import cl.bibliotecaproyecto.autores.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreAutorAndApellidoAutor(
            String nombreAutor, String apellidoAutor);
}
