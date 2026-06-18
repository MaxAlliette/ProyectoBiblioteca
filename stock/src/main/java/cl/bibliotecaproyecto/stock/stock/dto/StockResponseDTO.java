package cl.bibliotecaproyecto.stock.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de un stock")
public class StockResponseDTO {
    @Schema(description = "ID de Stock", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idStock;
    @Schema(description = "Cantidad de libros en el Stock", example = "1")
    private Integer cantidad;
    @Schema(description = "Estado en el que se encuentra el Stock", example = "Bien")
    private String estado_ejemplar;

    @Schema(description = "Nombre del libro del Stock", example = "Harry Potter")
    private String tituloLibro;
}
