package cl.bibliotecaproyecto.libros.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de un Libro")
public class LibroResponseDTO {

    @Schema(description = "ID del libro", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idLibro;

    @Schema(description = "isbn unico del libro",
            example = "978-01")
    private String isbnLibro;

    @Schema(description = "Titulo del libro",
            example = "Metamorfosis")
    private String tituloLibro;

    @Schema(description = "Año del libro",
            example = "1967")
    private Integer anioLibro;

    @Schema(description = "Nombre de la Categoria",
            example = "Drama")
    private String nombreCategoria;

    @Schema(description = "Nombre del Autor",
            example = "Kafka")
    private String nombreAutor;


}
