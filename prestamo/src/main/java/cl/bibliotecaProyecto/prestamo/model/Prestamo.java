package cl.bibliotecaProyecto.prestamo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Column(nullable = false, length = 20)
    private String estadoPrestamo;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Long idStock;
}
