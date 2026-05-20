package cl.bibliotecaproyecto.rol.repository;

import cl.bibliotecaproyecto.rol.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
