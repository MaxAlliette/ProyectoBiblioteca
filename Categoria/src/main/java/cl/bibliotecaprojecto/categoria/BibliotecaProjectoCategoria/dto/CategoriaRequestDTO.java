package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDTO {
    @Schema(description = "Nombre de la categoria", example = "Comedia")
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombreCategoria;
}
