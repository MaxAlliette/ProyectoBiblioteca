package cl.bibliotecaProyecto.prestamo.dto;

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

    @NotNull(message = "La fecha inicial es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha limite es obligatoria")
    @Future(message = "La fecha limite no puede ser anterior a la fecha actual")
    private LocalDate fechaLimite;

    @NotBlank(message = "El estado del prestamo es obligatorio y no puede estar vacio.")
    private String estadoPrestamo;

    @NotNull(message = "El idUsuario es obligatorio.")
    @Positive(message = "El id de usuario debe ser mayor a 0")
    private Long idUsuario;

    @NotNull(message = "El idEjemplar es obligatorio.")
    @Positive(message = "El id de stock debe ser mayor a 0")
    private Long idStock;
}
