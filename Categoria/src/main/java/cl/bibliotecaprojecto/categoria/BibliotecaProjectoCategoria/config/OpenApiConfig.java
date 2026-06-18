package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Categoria - BibliotecaProyecto")
                        .version("1.0")
                        .description("Catalogo de autores de BibliotecaProyecto")
                        .contact(new Contact()
                                .name("Nicolas Contador")
                                .email("ncontadorvega@gmail.com")));
    }
}
