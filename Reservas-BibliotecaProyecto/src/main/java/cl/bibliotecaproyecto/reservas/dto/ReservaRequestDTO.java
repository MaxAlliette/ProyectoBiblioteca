package cl.bibliotecaproyecto.reservas.dto;

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
    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser antes de la fecha actual")
    private LocalDate fechaReserva;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicioReserva;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFinReserva;

    @NotNull(message = "El id de usuario es obligatoria")
    @Positive(message = "El id de usuario debe ser mayor a 0")
    private Long idUsuario;

    @NotNull(message = "El id de sala es obligatoria")
    @Positive(message = "El id de sala debe ser mayor a 0")
    private Long idSala;
}
