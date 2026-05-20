package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.service;

import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto.CategoriaRequestDTO;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto.CategoriaResponseDTO;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.model.Categoria;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    private CategoriaResponseDTO mapToDTO(Categoria categoria){
        return new CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNombreCategoria()
        );
    }

    public List<CategoriaResponseDTO> obtenerTodos(){
        return categoriaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<CategoriaResponseDTO> buscarPorNombreCategoria(String nombreCategoria) {
        return categoriaRepository.findByNombreCategoria(nombreCategoria)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CategoriaResponseDTO guardar(CategoriaRequestDTO dto){
        Categoria categoria = new Categoria(
                null,
                dto.getNombreCategoria()
        );
        return mapToDTO(categoriaRepository.save(categoria));
    }

    public Optional<CategoriaResponseDTO> actualizar(Long id, CategoriaRequestDTO dto){
        return categoriaRepository.findById(id).map( existente ->
        {
            existente.setNombreCategoria(dto.getNombreCategoria());
            return mapToDTO(categoriaRepository.save(existente));
        });
    }

    public void eliminar(Long id){categoriaRepository.deleteById(id);}

    public Optional<CategoriaResponseDTO> obtenerPorId(Long id){
        return categoriaRepository.findById(id).map(this::mapToDTO);
    }
    }
