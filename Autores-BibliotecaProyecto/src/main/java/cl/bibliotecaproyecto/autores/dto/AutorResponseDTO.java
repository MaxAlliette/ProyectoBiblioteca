package cl.bibliotecaproyecto.autores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorResponseDTO {
    private Long idAutor;
    private String nombreAutor;
    private String apellidoAutor;
}
