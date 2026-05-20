package cl.bibliotecaproyecto.reservas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @Column(nullable = false)
    private LocalDate fechaReserva;

    @Column(nullable = false)
    private LocalTime horaInicioReserva;

    @Column(nullable = false)
    private LocalTime horaFinReserva;

    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Long idSala;
}
