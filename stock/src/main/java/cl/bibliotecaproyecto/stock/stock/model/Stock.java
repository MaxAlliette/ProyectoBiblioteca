package cl.bibliotecaproyecto.stock.stock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStock;

    @Column(nullable = false, scale = 2)
    private Integer cantidadStock;

    @Column(nullable = false, length = 20)
    private String estado_ejemplar;

    @Column(nullable = false)
    private Long idLibro;
}
