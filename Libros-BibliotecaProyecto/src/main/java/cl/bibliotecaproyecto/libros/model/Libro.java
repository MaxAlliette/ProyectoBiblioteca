package cl.bibliotecaproyecto.libros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @Column(nullable = false, length = 20)
    private String isbnLibro;

    @Column(nullable = false, length = 200)
    private String tituloLibro;

    @Column(nullable = false)
    private Integer anioLibro;

    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    @Column(name = "id_autor", nullable = false)
    private Long idAutor;
}
