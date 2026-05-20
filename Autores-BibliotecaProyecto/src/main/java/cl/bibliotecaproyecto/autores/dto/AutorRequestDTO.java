package cl.bibliotecaproyecto.autores.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombreAutor;

    @NotBlank(message = "El apellido no puede estar vacío.")
    private String apellidoAutor;
}
