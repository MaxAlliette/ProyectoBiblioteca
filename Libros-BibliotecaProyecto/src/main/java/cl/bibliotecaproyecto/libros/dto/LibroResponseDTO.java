package cl.bibliotecaproyecto.libros.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroResponseDTO {

    private Long idLibro;
    private String isbnLibro;
    private String tituloLibro;
    private Integer anioLibro;
    private String nombreCategoria;
    private String nombreAutor;


}
