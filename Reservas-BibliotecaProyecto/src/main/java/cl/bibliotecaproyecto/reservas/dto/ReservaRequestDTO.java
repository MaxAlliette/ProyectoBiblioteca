package cl.bibliotecaproyecto.reservas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequestDTO {
    @Schema(description = "Fecha de reserva", example = "2026-05-27")
    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser antes de la fecha actual")
    private LocalDate fechaReserva;

    @Schema(description = "Hora de incio de reserva", example = "14:00")
    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicioReserva;

    @Schema(description = "Hora de fin de reserva", example = "16:00")
    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFinReserva;

    @Schema(description = "id de usuario que reserva", example = "1")
    @NotNull(message = "El id de usuario es obligatoria")
    @Positive(message = "El id de usuario debe ser mayor a 0")
    private Long idUsuario;

    @Schema(description = "id de sala que se reserva", example = "1")
    @NotNull(message = "El id de sala es obligatoria")
    @Positive(message = "El id de sala debe ser mayor a 0")
    private Long idSala;
}
