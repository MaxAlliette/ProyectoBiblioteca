package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.config;

import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.model.Categoria;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() > 0) {
            log.info(">>> DataInitializer: la BD ya tiene datos, se omite la carga inicial.");
            return;
        }
        log.info(">>> DataInitializer: BD vacía detectada, insertando datos de prueba...");

        Categoria prog = categoriaRepository.save(
                new Categoria(null, "Programación"));
        Categoria en = categoriaRepository.save(
                new Categoria(null, "Ensayo"));
        Categoria no = categoriaRepository.save(
                new Categoria(null, "Novela"));
    }

}
