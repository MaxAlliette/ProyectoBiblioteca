package cl.bibliotecaProyecto.prestamo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoRequestDTO {

    @Schema(description = "Fecha de inicio del prestamo", example = "2026-04-21")
    @NotNull(message = "La fecha inicial es obligatoria")
    private LocalDate fechaInicio;

    @Schema(description = "Fecha Limite del prestamo", example = "2026-04-29")
    @NotNull(message = "La fecha limite es obligatoria")
    @Future(message = "La fecha limite no puede ser anterior a la fecha actual")
    private LocalDate fechaLimite;

    @Schema(description = "Estado en el que se encuentra un prestamo", example = "Activo")
    @NotBlank(message = "El estado del prestamo es obligatorio y no puede estar vacio.")
    private String estadoPrestamo;

    @Schema(description = "ID de usuario", example = "1")
    @NotNull(message = "El idUsuario es obligatorio.")
    @Positive(message = "El id de usuario debe ser mayor a 0")
    private Long idUsuario;

    @Schema(description = "ID de stock", example = "1")
    @NotNull(message = "El idEjemplar es obligatorio.")
    @Positive(message = "El id de stock debe ser mayor a 0")
    private Long idStock;
}
