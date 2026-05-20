package cl.bibliotecaproyecto.autores.service;

import cl.bibliotecaproyecto.autores.dto.AutorRequestDTO;
import cl.bibliotecaproyecto.autores.dto.AutorResponseDTO;
import cl.bibliotecaproyecto.autores.model.Autor;
import cl.bibliotecaproyecto.autores.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AutorService {
    private final AutorRepository autorRepository;

    private AutorResponseDTO mapToDTO (Autor autor) {
        return new AutorResponseDTO(
                autor.getIdAutor(),
                autor.getNombreAutor(),
                autor.getApellidoAutor()
        );
    }

    //OBTENER TODOS
    public List<AutorResponseDTO> obtenerTodos(){
        return autorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // OBTENER POR ID
    public Optional<AutorResponseDTO> obtenerPorId(Long id){
        return autorRepository.findById(id).map(this::mapToDTO);
    }

    // OBTENER POR NOMBRE Y APELLIDO
    public List<AutorResponseDTO> buscarPorNombreAndApellido(String nombreAutor, String apellidoAutor) {
        return autorRepository.findByNombreAutorAndApellidoAutor(nombreAutor, apellidoAutor)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // GUARDAR
    public AutorResponseDTO guardar(AutorRequestDTO dto){

        Autor autor = new Autor(
                null,
                dto.getNombreAutor(),
                dto.getApellidoAutor()
        );
        return mapToDTO(autorRepository.save(autor));
    }

    // ACTUALIZAR
    public Optional<AutorResponseDTO> actualizar(Long id, AutorRequestDTO dto){
        return autorRepository.findById(id).map( existente ->
        {
            existente.setNombreAutor(dto.getNombreAutor());
            existente.setApellidoAutor(dto.getApellidoAutor());
            return mapToDTO(autorRepository.save(existente));
        });
    }

    // ELIMINAR
    public void eliminar(Long id){
        autorRepository.deleteById(id);
    }
}
