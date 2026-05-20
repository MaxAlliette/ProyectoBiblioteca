package cl.bibliotecaproyecto.libros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorDTO {
    private Long idAutor;
    private String nombreAutor;
    private String apellidoAutor;
}
