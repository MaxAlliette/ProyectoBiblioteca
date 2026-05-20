package cl.bibliotecaproyecto.autores.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Autor")
public class Autor {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idAutor;

    @Column(nullable = false, length = 100)
    private String nombreAutor;

    @Column(nullable = false, length = 100)
    private String apellidoAutor;
}

