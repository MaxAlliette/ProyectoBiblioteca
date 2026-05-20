package cl.bibliotecaproyecto.rol.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolRequestDTO {

    @NotBlank(message = "El nombre de rol no puede estar vacio")
    private String nombreRol;

    @NotBlank(message = "La descripcion debe tener informacion")
    private String descripcionRol;
}
