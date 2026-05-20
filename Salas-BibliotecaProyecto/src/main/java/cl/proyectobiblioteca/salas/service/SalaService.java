package cl.proyectobiblioteca.salas.service;

import cl.proyectobiblioteca.salas.dto.SalaRequestDTO;
import cl.proyectobiblioteca.salas.dto.SalaResponseDTO;
import cl.proyectobiblioteca.salas.model.Sala;
import cl.proyectobiblioteca.salas.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalaService {
    private final SalaRepository salaRepository;

    private SalaResponseDTO mapToDTO(Sala sala) {
        return new SalaResponseDTO(
                sala.getIdSala(),
                sala.getNombreSala(),
                sala.getCapacidadSala()
        );
    }

    //OBTENER TODOS
    public List<SalaResponseDTO> obtenerTodos(){
        return salaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // OBTENER POR ID
    public Optional<SalaResponseDTO> obtenerPorId(Long id){
        return salaRepository.findById(id).map(this::mapToDTO);
    }

    // GUARDAR
    public SalaResponseDTO guardar(SalaRequestDTO dto){

        Sala sala = new Sala(
                null,
                dto.getNombreSala(),
                dto.getCapacidadSala()
        );
        return mapToDTO(salaRepository.save(sala));
    }

    // ACTUALIZAR
    public Optional<SalaResponseDTO> actualizar(Long id, SalaRequestDTO dto){
        return salaRepository.findById(id).map( existente ->
        {
            existente.setNombreSala(dto.getNombreSala());
            existente.setCapacidadSala(dto.getCapacidadSala());
            return mapToDTO(salaRepository.save(existente));
        });
    }

    // ELIMINAR
    public void eliminar(Long id){
        salaRepository.deleteById(id);
    }

    //OBTENER POR NOMBRE
    public List<SalaResponseDTO> buscarPorNombreSala(String nombreSala){
        return salaRepository.findByNombreSala(nombreSala)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}

