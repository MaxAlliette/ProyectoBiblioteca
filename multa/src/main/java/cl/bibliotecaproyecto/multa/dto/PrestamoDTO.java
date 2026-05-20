package cl.bibliotecaproyecto.multa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoDTO {

    private Long idPrestamo;
    private LocalDate fechaInicio;
    private LocalDate fechaLimite;
    private String estadoPrestamo;
    private String nombreUsuario;
    private Long idStock;

}
