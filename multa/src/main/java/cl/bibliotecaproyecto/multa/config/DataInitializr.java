package cl.bibliotecaproyecto.multa.config;

import cl.bibliotecaproyecto.multa.model.Multa;
import cl.bibliotecaproyecto.multa.repository.MultaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializr implements CommandLineRunner {
    private final MultaRepository multaRepository;

    @Override
    public void run(String... args){
        if (multaRepository.count() > 0){
            log.info("Multas ya cargadas. Se omite inicializacion");
            return;
        }
        log.info("Cargando multas iniciales...");
        multaRepository.save(new Multa(null, new java.math.BigDecimal("20000.00"), "Pendiente", 1L));
        multaRepository.save(new Multa(null, new java.math.BigDecimal("80000.00"), "Pago Parcial", 2L));
        log.info("Multas Cargadas");
    }

}
