package cl.bibliotecaProyecto.prestamo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    private Long idStock;
    private Integer cantidadStock;
    private String estado_ejemplar;
    private String libroNombre;
}
