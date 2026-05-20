package cl.bibliotecaproyecto.rol.config;

import cl.bibliotecaproyecto.rol.model.Rol;
import cl.bibliotecaproyecto.rol.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;

    @Override
    public void run(String... args){
        if (rolRepository.count() > 0){
            log.info(">>> DataInitializer: la BD ya tiene datos, se omite la carga de datos inicial.");
            return;
        }
        log.info("DataInitializer: BD vacia detectada, insertando dato para prueba");

        rolRepository.save( new Rol(null, "Admin",
                        "Perfil con privilegios elevados encargado de la gestión integral, configuración y mantenimiento del entorno."));

        rolRepository.save( new Rol(null, "Cliente",
                "Perfil con privilegios limitados solo a la renta, utilizacion y compra de libros, a su vez la reserva de salas de estudio privadas"));


    }
}
