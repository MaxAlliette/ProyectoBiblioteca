package cl.bibliotecaproyecto.autores.config;

import cl.bibliotecaproyecto.autores.model.Autor;
import cl.bibliotecaproyecto.autores.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AutorRepository autorRepository;

    @Override
    public void run(String... args){
        if (autorRepository.count() > 0){
            log.info("La BD ya tiene datos, se omite la carga inicial");
            return;
        }
        log.info("La BD está vacía. Insertando datos de prueba...");
        autorRepository.save(new Autor(null, "J.K.","Rowling"));
        autorRepository.save(new Autor(null, "Franz","Kafka"));
        autorRepository.save(new Autor(null, "John","Guttag"));
        autorRepository.save(new Autor(null, "Virginia","Woolf"));
    }
}
