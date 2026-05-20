package cl.bibliotecaproyecto.libros.service;


import cl.bibliotecaproyecto.libros.dto.AutorDTO;
import cl.bibliotecaproyecto.libros.dto.CategoriaDTO;
import cl.bibliotecaproyecto.libros.dto.LibroRequestDTO;
import cl.bibliotecaproyecto.libros.dto.LibroResponseDTO;
import cl.bibliotecaproyecto.libros.model.Libro;
import cl.bibliotecaproyecto.libros.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroService {
    private final LibroRepository libroRepository;
    private final WebClient autorWebClient;
    private final WebClient categoriaWebClient;

    private LibroResponseDTO mapToDTO(Libro libro){
        AutorDTO autor = autorWebClient.get()
                .uri("/{id}", libro.getIdAutor())
                .retrieve()
                .bodyToMono(AutorDTO.class)
                .block();

        CategoriaDTO categoria = categoriaWebClient.get()
                .uri("/{id}", libro.getIdCategoria())
                .retrieve()
                .bodyToMono(CategoriaDTO.class)
                .block();

        return new LibroResponseDTO(
                libro.getIdLibro(),
                libro.getIsbnLibro(),
                libro.getTituloLibro(),
                libro.getAnioLibro(),
                categoria != null ? categoria.getNombreCategoria() : "Categoria no disponible",
                autor != null ? autor.getNombreAutor() : "Autor no disponible"
        );
    }

    public List<LibroResponseDTO> obtenerTodos(){
        return libroRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<LibroResponseDTO> obtenerPorId(Long id){
        return libroRepository.findById(id).map(this::mapToDTO);
    }
    //guardar
    public LibroResponseDTO guardar(LibroRequestDTO dto){
            Libro libro = new Libro(
                    null,
                    dto.getIsbnLibro(),
                    dto.getTituloLibro(),
                    dto.getAnioLibro(),
                    dto.getIdCategoria(),
                    dto.getIdAutor()
            );
            return mapToDTO(libroRepository.save(libro));
        }

    //actualizar
    public Optional<LibroResponseDTO> actualizar(Long id, LibroRequestDTO dto) {
        return libroRepository.findById(id).map(existente -> {

            existente.setIsbnLibro(dto.getIsbnLibro());
            existente.setTituloLibro(dto.getTituloLibro());
            existente.setAnioLibro(dto.getAnioLibro());
            existente.setIdCategoria(dto.getIdCategoria());
            existente.setIdAutor(dto.getIdAutor());

            return mapToDTO(libroRepository.save(existente));
        });
    }

    //eliminar
    public void eliminar(Long id){
        libroRepository.deleteById(id);
    }

    //buscar por titulo y categoria
    public List<LibroResponseDTO> buscarPorTitulo(String texto) {
        return libroRepository.findByTituloLibroContainingIgnoreCase(texto)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<LibroResponseDTO> buscarPorCategoria(Long categoriaId) {
        return libroRepository.findByIdCategoria(categoriaId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
