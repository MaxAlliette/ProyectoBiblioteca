package cl.bibliotecaproyecto.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {

    @Schema(description = "Nombre del usuario", example = "Camilo B")
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String nombreUsuario;

    @Schema(description = "Apellido paterno del usuario", example = "Baeza")
    @NotBlank(message = "El apellido paterno del usuario es obligatorio y no puede estar vacío")
    private String apellidoPaternoUsuario;

    @Schema(description = "Apellido materno del usuario", example = "Polanco")
    @NotBlank(message = "El apellido materno del usuario es obligatorio y no puede estar vacío")
    private String apellidoMaternoUsuario;

    @Schema(description = "Correo del usuario", example = "camilob@gmail.com")
    @NotBlank(message = "El correo del usuario es obligatorio y no puede estar vacío")
    @Email(message = "Debe escribir un correo válido")
    private String correoUsuario;

    @Schema(description = "Estado del usuario", example = "habilitado")
    @NotBlank(message = "El estado de usuario es obligatorio y no puede estar vacío")
    private String estadoUsuario;

    @Schema(description = "id del rol del usuario", example = "2")
    @NotNull(message = "El idRoles es obligatorio")
    private Long idRol;
}
