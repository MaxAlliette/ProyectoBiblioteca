package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombreCategoria;
}
