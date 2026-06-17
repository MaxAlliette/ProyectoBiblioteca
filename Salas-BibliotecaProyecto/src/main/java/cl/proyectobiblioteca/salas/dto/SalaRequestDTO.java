package cl.proyectobiblioteca.salas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaRequestDTO {

    @Schema(description = "Nombre de sala", example = "Sala 1")
    @NotBlank(message = "El nombre de la sala no puede estar vacío.")
    private String nombreSala;

    @Schema(description = "Capacidad de sala", example = "8")
    @NotNull(message = "La capacidad de la sala es obligatoria")
    @Positive (message = "La capacidad debe ser mayor a 0.")
    private Integer capacidadSala;
}
