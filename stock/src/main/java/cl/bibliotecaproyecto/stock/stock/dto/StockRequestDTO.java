package cl.bibliotecaproyecto.stock.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRequestDTO {

    @Schema(description = "Cantidad de libros en el Stock", example = "1")
    @NotNull(message = "La cantidad es obligatoria.")
    @PositiveOrZero(message = "La cantidad no puede ser negativa.")
    private Integer cantidad;

    @Schema(description = "Estado en el que se encuentra el Stock", example = "Bien")
    @NotBlank(message = "El estado no puede estar vacio.")
    private String estado_ejemplar;

    @Schema(description = "ID del libro en el stock", example = "Harry Potter")
    @NotNull(message = "El libroId es obligatorio")
    private Long libroId;
}
