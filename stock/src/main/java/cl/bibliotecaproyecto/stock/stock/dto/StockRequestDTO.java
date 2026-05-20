package cl.bibliotecaproyecto.stock.stock.dto;

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

    @NotNull(message = "La cantidad es obligatoria.")
    @PositiveOrZero(message = "La cantidad no puede ser negativa.")
    private Integer cantidad;

    @NotBlank(message = "El estado no puede estar vacio.")
    private String estado_ejemplar;

    @NotNull(message = "El libroId es obligatorio")
    private Long libroId;
}
