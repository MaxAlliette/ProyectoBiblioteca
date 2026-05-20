package cl.bibliotecaproyecto.stock.stock.config;

import cl.bibliotecaproyecto.stock.stock.model.Stock;
import cl.bibliotecaproyecto.stock.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final StockRepository stockRepository;

    @Override
    public void run(String... args) {
        if (stockRepository.count() > 0) {
            log.info(">>> DataInitializer: la BD ya tiene datos, se omite la carga inicial.");
            return;
        }

        log.info(">>> DataInitializer: BD vacía detectada, insertando datos de prueba...");

        stockRepository.save(new Stock(null, 10, "Bien", 2L));
        stockRepository.save(new Stock(null, 20, "Bien", 1L));
        stockRepository.save(new Stock(null, 30, "Medio", 3L));
        stockRepository.save(new Stock(null, 5, "Mal", 1L));
        log.info("Stocks cargados.");
    }
}