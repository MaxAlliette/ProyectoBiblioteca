package cl.bibliotecaproyecto.libros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Long idCategoria;
    private String nombreCategoria;
}
