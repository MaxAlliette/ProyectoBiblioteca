package cl.bibliotecaproyecto.multa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "multas")
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMulta;

    @Column(nullable = false)
    private BigDecimal montoMulta;

    @Column(nullable = false, length = 20)
    private String estadoMulta;

    @Column(nullable = false)
    private Long idPrestamo;
}
