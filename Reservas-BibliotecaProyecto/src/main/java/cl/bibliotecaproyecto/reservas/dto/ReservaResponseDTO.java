package cl.bibliotecaproyecto.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de una reserva")
public class ReservaResponseDTO {
    @Schema(description = "ID de la reserva ", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idReserva;
    @Schema(description = "fecha de reserva", example = "2026-05-27")
    private LocalDate fechaReserva;
    @Schema(description = "hora de inicio de reserva", example = "14:00")
    private LocalTime horaInicioReserva;
    @Schema(description = "hora de fin de reserva", example = "16:00")
    private LocalTime horaFinReserva;
    @Schema(description = "Nombre de usuario que hace la reserva", example = "Camilo")
    private String nombreUsuario;
    @Schema(description = "Nombre de sala que se reserva", example = "Sala 1")
    private String nombreSala;
}
