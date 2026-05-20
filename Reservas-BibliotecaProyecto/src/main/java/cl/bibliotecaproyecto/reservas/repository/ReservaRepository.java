package cl.bibliotecaproyecto.reservas.repository;

import cl.bibliotecaproyecto.reservas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByIdSala(Long idSala);
    List<Reserva> findByIdUsuario(Long idUsuario);
}
