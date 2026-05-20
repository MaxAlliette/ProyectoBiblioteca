package cl.proyectobiblioteca.salas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaResponseDTO {
    private Long idSala;
    private String nombreSala;
    private Integer capacidadSala;
}
