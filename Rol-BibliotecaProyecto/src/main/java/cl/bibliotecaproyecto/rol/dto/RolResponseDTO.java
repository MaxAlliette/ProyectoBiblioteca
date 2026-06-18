package cl.bibliotecaproyecto.rol.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de un Rol")
public class RolResponseDTO {

    @Schema(description = "ID del Rol", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idRol;

    @Schema(description = "Nombre del Rol",
            example = "Admin")
    private String nombreRol;

    @Schema(description = "Descripcion del Rol",
            example = "El rol puede modifica y mover lo que desee")
    private String descripcionRol;

}
