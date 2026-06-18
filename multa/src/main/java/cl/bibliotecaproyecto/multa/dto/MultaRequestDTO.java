package cl.bibliotecaproyecto.multa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultaRequestDTO {

    @Schema(description = "Estado de la Multa",
            example = "Pendiete")
    @NotBlank(message = "El estado no puede estar vacio")
    private String estadoMulta;

    @Schema(description = "Monto de la multa",
            example = "20000.00")
    @NotNull(message = "El monto es obligatorio")
    private BigDecimal montoMulta;

    @Schema(description = "ID del prestamo",
            example = "1")
    @NotNull(message = "El prestamoId es obligatorio")
    private Long idPrestamo;



}
