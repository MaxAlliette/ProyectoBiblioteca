package cl.bibliotecaproyecto.multa.repository;


import cl.bibliotecaproyecto.multa.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultaRepository extends JpaRepository<Multa, Long> {
    List<Multa> findByIdPrestamoIn(List<Long> idPrestamos);
}
