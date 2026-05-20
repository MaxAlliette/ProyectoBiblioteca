package cl.bibliotecaproyecto.rol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolResponseDTO {

    private Long idRol;
    private String nombreRol;
    private String descripcionRol;

}
