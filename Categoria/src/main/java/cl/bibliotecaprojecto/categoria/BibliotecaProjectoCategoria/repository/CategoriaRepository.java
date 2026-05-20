package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.repository;

import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByNombreCategoria(
            String nombreCategoria);
}
