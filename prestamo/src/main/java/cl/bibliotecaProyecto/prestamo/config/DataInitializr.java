package cl.bibliotecaProyecto.prestamo.config;

import cl.bibliotecaProyecto.prestamo.model.Prestamo;
import cl.bibliotecaProyecto.prestamo.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializr implements CommandLineRunner {
    private final PrestamoRepository prestamoRepository;

    @Override
    public void run(String... args) {
        if (prestamoRepository.count() > 0) {
            log.info("Prestamos ya cargados. Se omite inicialización.");
            return;
        }
        log.info("Cargando prestamos iniciales...");
        prestamoRepository.save(new Prestamo(null, LocalDate.parse("2026-03-13"), LocalDate.parse("2026-06-13"), "Activo", 2L, 1L));
        prestamoRepository.save(new Prestamo(null, LocalDate.parse("2026-04-21"), LocalDate.parse("2026-07-21"), "Atrasado", 1L, 3L));
        prestamoRepository.save(new Prestamo(null, LocalDate.parse("2026-02-17"), LocalDate.parse("2026-06-17"), "Atrasado", 3L, 2L));

        log.info("Prestamos cargados.");
    }
}