package cl.bibliotecaProyecto.prestamo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Datos de una categoria")
public class PrestamoResponseDTO {
    @Schema(description = "ID de prestamo", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idPrestamo;
    @Schema(description = "Fecha de inicio del prestamo", example = "2026-04-21")
    private LocalDate fechaInicio;
    @Schema(description = "Fecha Limite del prestamo", example = "2026-04-29")
    private LocalDate fechaLimite;
    @Schema(description = "Estado en el que se encuentra un prestamo", example = "Activo")
    private String estadoPrestamo;
    @Schema(description = "Nombre de un usuario", example = "Juan Pablo")
    private String nombreUsuario;
    @Schema(description = "ID de stock", example = "1")
    private Long idStock;
}
