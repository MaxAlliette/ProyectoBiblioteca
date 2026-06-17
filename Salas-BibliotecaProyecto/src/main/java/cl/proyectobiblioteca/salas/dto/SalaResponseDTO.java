package cl.proyectobiblioteca.salas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de una sala")
public class SalaResponseDTO {
    @Schema(description = "ID de la sala", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idSala;

    @Schema(description = "Nombre de la sala", example = "Sala 1")
    private String nombreSala;

    @Schema(description = "Capacidad de la sala", example = "8")
    private Integer capacidadSala;
}
