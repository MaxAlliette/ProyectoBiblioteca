package cl.bibliotecaProyecto.prestamo.config;

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

    @Value("${ms.stocks.url}")
    private String stocksUrl;

    @Bean
    public WebClient stockWebClient(){
        return WebClient.builder()
                .baseUrl(stocksUrl)
                .build();
    }
}
