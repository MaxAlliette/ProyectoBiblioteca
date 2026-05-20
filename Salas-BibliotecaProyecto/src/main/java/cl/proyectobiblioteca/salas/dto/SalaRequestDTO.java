package cl.proyectobiblioteca.salas.dto;

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

    @NotBlank(message = "El nombre de la sala no puede estar vacío.")
    private String nombreSala;

    @NotNull(message = "La capacidad de la sala es obligatoria")
    @Positive (message = "La capacidad debe ser mayor a 0.")
    private Integer capacidadSala;
}
