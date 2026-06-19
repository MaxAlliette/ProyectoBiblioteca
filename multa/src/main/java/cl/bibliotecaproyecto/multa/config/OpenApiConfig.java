package cl.bibliotecaproyecto.multa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info()
                .title("Multas - BibliotecaProyecto")
                .version("1.0")
                .description("Gestión de multas de BibliotecaProyecto")
                .contact(new Contact()
                        .name("Benjamin Figueroa")
                        .email("benja.figu@gmail.com")));
    }
}
