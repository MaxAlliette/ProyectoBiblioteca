package cl.bibliotecaproyecto.libros.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${ms.categorias.url}")
    private String categoriasUrl;

    @Bean
    public WebClient categoriaWebClient() {
        return WebClient.builder()
                .baseUrl(categoriasUrl)
                .build();
    }

    @Value("${ms.autores.url}")
    private String autoresUrl;

    @Bean
    public WebClient autorWebClient() {
        return WebClient.builder()
                .baseUrl(autoresUrl)
                .build();
    }

}
