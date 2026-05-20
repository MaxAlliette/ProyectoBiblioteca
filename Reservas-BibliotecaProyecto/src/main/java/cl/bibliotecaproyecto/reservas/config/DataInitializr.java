package cl.bibliotecaproyecto.reservas.config;

import cl.bibliotecaproyecto.reservas.model.Reserva;
import cl.bibliotecaproyecto.reservas.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializr implements CommandLineRunner {
    private final ReservaRepository reservaRepository;

    @Override
    public void run(String... args){
        if (reservaRepository.count()>0){
            log.info("La BD ya tiene datos, se omite la carga inicial");
            return;
        }
        log.info("La BD está vacía. Insertando datos de prueba...");
        reservaRepository.save(new Reserva(null, LocalDate.parse("2026-05-27"), LocalTime.parse("14:00"), LocalTime.parse("16:00"), 1L, 1L));
        reservaRepository.save(new Reserva(null, LocalDate.parse("2026-05-30"), LocalTime.parse("10:00"), LocalTime.parse("11:00"), 3L, 2L));
        }
    }

