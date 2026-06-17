package cl.bibliotecaproyecto.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de un usuario")
public class UsuarioResponseDTO {
    @Schema(description = "ID del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idUsuario;
    @Schema(description = "Nombre del usuario", example = "Camilo")
    private String nombreUsuario;
    @Schema(description = "Apellido paterno del usuario", example = "Baeza")
    private String apellidoPaternoUsuario;
    @Schema(description = "Apellido materno del usuario", example = "Polanco")
    private String apellidoMaternoUsuario;
    @Schema(description = "correo del usuario", example = "camilob@gmail.com")
    private String correoUsuario;
    @Schema(description = "estado del usuario", example = "habilitado")
    private String estadoUsuario;
    @Schema(description = "rol del usuario", example = "cliente")
    private String nombreRol;
}
