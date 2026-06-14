package cl.bibliotecaproyecto.autores.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de un autor")
public class AutorResponseDTO {
    @Schema(description = "ID del autor", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idAutor;
    @Schema(description = "Nombre del autor", example = "Hermann")
    private String nombreAutor;

    @Schema(description = "Apellido del autor", example = "Hesse")
    private String apellidoAutor;
}
