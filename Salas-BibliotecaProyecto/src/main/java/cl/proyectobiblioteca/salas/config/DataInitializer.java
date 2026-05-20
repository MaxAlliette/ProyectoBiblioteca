package cl.proyectobiblioteca.salas.config;

import cl.proyectobiblioteca.salas.model.Sala;
import cl.proyectobiblioteca.salas.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SalaRepository salaRepository;

    @Override
    public void run(String... args) {
        if (salaRepository.count() > 0) {
            log.info("La BD ya tiene datos, se omite la carga inicial");
            return;
        }
        log.info("La BD está vacía. Insertando datos de prueba...");
        salaRepository.save(new Sala(null, "Sala 1", 8));
        salaRepository.save(new Sala(null, "Sala 2", 5));
        salaRepository.save(new Sala(null, "Sala 3", 6));
    }
}
