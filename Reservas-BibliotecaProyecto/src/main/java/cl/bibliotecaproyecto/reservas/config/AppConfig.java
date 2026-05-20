package cl.bibliotecaproyecto.reservas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Value("${ms.usuarios.url}")
    private String usuariosUrl;

    @Bean
    public WebClient usuarioWebClient(){
        return WebClient.builder()
                .baseUrl(usuariosUrl)
                .build();
    }

    @Value("${ms.salas.url}")
    private String salasUrl;

    @Bean
    public WebClient salaWebClient(){
        return WebClient.builder()
                .baseUrl(salasUrl)
                .build();
    }
}
