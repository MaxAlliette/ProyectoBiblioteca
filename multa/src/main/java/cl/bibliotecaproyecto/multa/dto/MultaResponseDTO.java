package cl.bibliotecaproyecto.multa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de la Multa")
public class MultaResponseDTO {

    @Schema(description = "ID de la multa", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idMulta;

    @Schema(description = "Estado de la Multa",
            example = "Pendiete")
    private BigDecimal montoMulta;

    @Schema(description = "Monto de la multa",
            example = "20000.00")
    private String estadoMulta;

    @Schema(description = "ID del prestamo",
            example = "1")
    private Long idPrestamo;

}
