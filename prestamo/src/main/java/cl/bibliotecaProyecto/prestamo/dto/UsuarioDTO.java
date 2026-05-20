package cl.bibliotecaProyecto.prestamo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long idUsuario;
    private String nombreUsuario;
    private String apellidoPaternoUsuario;
    private String apellidoMaternoUsuario;
    private String correoUsuario;
    private String estadoUsuario;
    private String nombreRol;
}
