package cl.bibliotecaproyecto.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaDTO {
    private Long idSala;
    private String nombreSala;
    private Integer capacidadSala;
}
