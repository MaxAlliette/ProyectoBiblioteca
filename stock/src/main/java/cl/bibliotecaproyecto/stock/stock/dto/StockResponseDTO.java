package cl.bibliotecaproyecto.stock.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponseDTO {

    private Long idStock;
    private Integer cantidad;
    private String estado_ejemplar;

    private String tituloLibro;
}
