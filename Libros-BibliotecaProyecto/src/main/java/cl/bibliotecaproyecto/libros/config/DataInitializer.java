package cl.bibliotecaproyecto.libros.config;

import cl.bibliotecaproyecto.libros.model.Libro;
import cl.bibliotecaproyecto.libros.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final LibroRepository libroRepository;

    @Override
    public void run(String... args){

        // 1. Verificamos si ya hay libros para no duplicar datos cada vez que reinicies
        if (libroRepository.count() > 0) {
            log.info("La base de datos de Libros ya tiene datos, se omite la carga inicial.");
            return;
        }

        log.info("BD vacia detectada: Insertando libros de prueba...");


        libroRepository.save(new Libro(null, "978-01", "El Quijote", 1605, 3L, 1L));
        libroRepository.save(new Libro(null, "978-02", "Cien Años de Soledad", 1967, 3L, 2L));
        libroRepository.save(new Libro(null, "978-03", "Harry Potter", 1997, 3L, 1L));
        libroRepository.save(new Libro(null, "978-04", "Clean Code", 2008, 1L, 3L));

        log.info("¡Carga inicial de libros completada con éxito!");
    }
}
