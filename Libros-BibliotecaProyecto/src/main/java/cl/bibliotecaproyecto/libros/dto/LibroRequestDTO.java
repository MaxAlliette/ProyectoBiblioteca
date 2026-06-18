package cl.bibliotecaproyecto.libros.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroRequestDTO {

    @Schema(description = "isbn unico del libro",
            example = "978-01")
    @NotBlank(message = "El ISBN no puede estar vacio.")
    private String isbnLibro;


    @Schema(description = "Titulo del libro",
            example = "Metamorfosis")
    @NotBlank(message = "El titulo no puede estar vacio")
    private String tituloLibro;


    @Schema(description = "Año del libro",
            example = "1967")
    @NotNull(message = "El año es obligatorio.")
    private Integer anioLibro;


    @Schema(description = "ID de la Categoria",
            example = "1")
    @NotNull(message = "El ID de la categoría es obligatorio.")
    private Long idCategoria;


    @Schema(description = "ID del Autor",
            example = "1")
    @NotNull(message = "El ID del autor es obligatorio.")
    private Long idAutor;

}
