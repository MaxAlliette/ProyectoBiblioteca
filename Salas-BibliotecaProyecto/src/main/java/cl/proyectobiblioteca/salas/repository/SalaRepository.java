package cl.proyectobiblioteca.salas.repository;

import cl.proyectobiblioteca.salas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    List<Sala> findByNombreSala(String nombreSala);
}
