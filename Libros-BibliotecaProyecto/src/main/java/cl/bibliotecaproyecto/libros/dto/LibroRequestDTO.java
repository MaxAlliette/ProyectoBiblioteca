package cl.bibliotecaproyecto.libros.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroRequestDTO {

    @NotBlank(message = "El ISBN no puede estar vacio.")
    private String isbnLibro;

    @NotBlank(message = "El titulo no puede estar vacio")
    private String tituloLibro;

    @NotNull(message = "El año es obligatorio.")
    private Integer anioLibro;

    @NotNull(message = "El ID de la categoría es obligatorio.")
    private Long idCategoria;

    @NotNull(message = "El ID del autor es obligatorio.")
    private Long idAutor;

}
