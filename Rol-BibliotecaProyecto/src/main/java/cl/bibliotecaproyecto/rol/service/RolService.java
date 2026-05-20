package cl.bibliotecaproyecto.rol.service;

import cl.bibliotecaproyecto.rol.dto.RolRequestDTO;
import cl.bibliotecaproyecto.rol.dto.RolResponseDTO;
import cl.bibliotecaproyecto.rol.model.Rol;
import cl.bibliotecaproyecto.rol.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;

    private RolResponseDTO mapToDTO(Rol rol){
        return new RolResponseDTO(
                rol.getIdRol(),
                rol.getNombreRol(),
                rol.getDescripcionRol()
        );
    }

    public List<RolResponseDTO> obtenerTodos(){
        return rolRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public RolResponseDTO guardar(RolRequestDTO dto){
        Rol rol = new Rol(
                null,
                dto.getNombreRol(),
                dto.getDescripcionRol()
        );
        return mapToDTO(rolRepository.save(rol));
    }

    public Optional<RolResponseDTO> actualizar(Long id, RolRequestDTO dto){
        return rolRepository.findById(id).map( existente ->
        {
            existente.setNombreRol(dto.getNombreRol());
            existente.setDescripcionRol(dto.getDescripcionRol());
            return mapToDTO(rolRepository.save(existente));
        });
    }

    public void eliminar(Long id){rolRepository.deleteById(id);}

    public Optional<RolResponseDTO> obtenerPorId(Long id){
        return rolRepository.findById(id).map(this::mapToDTO);
    }


}
