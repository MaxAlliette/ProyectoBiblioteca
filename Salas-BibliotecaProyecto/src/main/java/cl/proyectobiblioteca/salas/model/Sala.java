package cl.proyectobiblioteca.salas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Sala")
public class Sala {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idSala;

    @Column(nullable = false, length = 50)
    private String nombreSala;

    @Column(nullable = false)
    private Integer capacidadSala;
}
