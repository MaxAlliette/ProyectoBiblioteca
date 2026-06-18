package cl.bibliotecaproyecto.rol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolRequestDTO {

    @Schema(description = "Nombre del Rol",
            example = "Admin")
    @NotBlank(message = "El nombre de rol no puede estar vacio")
    private String nombreRol;

    @Schema(description = "Descripcion del Rol",
            example = "El rol puede modifica y mover lo que desee")
    @NotBlank(message = "La descripcion debe tener informacion")
    private String descripcionRol;
}
