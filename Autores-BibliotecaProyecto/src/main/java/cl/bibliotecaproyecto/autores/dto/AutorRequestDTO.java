package cl.bibliotecaproyecto.autores.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorRequestDTO {
    @Schema(description = "Nombre del autor", example = "Hermann")
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombreAutor;

    @Schema(description = "Apellido del autor", example = "Hermann")
    @NotBlank(message = "El apellido no puede estar vacío.")
    private String apellidoAutor;
}
