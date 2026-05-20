package cl.bibliotecaproyecto.reservas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponseDTO {

    private Long idReserva;
    private LocalDate fechaReserva;
    private LocalTime horaInicioReserva;
    private LocalTime horaFinReserva;
    private String nombreUsuario;
    private String nombreSala;
}
