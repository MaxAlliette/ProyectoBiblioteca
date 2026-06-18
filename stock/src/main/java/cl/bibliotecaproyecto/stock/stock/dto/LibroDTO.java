package cl.bibliotecaproyecto.stock.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  LibroDTO {
    private Long idLibro;
    private String isbnLibro;
    private String tituloLibro;
    private Integer anioLibro;
    private String nombreCategoria;
    private String nombreAutor;
}
