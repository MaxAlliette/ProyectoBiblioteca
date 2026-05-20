package cl.bibliotecaproyecto.multa.dto;

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

    @NotBlank(message = "El estado no puede estar vacio")
    private String estadoMulta;

    @NotNull(message = "El monto es obligatorio")
    private BigDecimal montoMulta;

    @NotNull(message = "El prestamoId es obligatorio")
    private Long idPrestamo;



}
