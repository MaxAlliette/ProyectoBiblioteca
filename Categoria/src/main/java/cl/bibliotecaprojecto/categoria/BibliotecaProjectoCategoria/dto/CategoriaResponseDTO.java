package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de una categoria")
public class CategoriaResponseDTO {
    @Schema(description = "ID de categoria", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idCategoria;
    @Schema(description = "Nombre de la categoria", example = "Comedia")
    private String nombreCategoria;
}
