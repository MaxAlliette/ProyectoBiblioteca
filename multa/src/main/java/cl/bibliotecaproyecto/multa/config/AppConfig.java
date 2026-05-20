package cl.bibliotecaproyecto.multa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Value("${ms.prestamos.url}")
    private String prestamosUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(prestamosUrl)
                .build();
    }
}
