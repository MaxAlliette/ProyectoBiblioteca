package cl.bibliotecaProyecto.prestamo.service;

import cl.bibliotecaProyecto.prestamo.dto.PrestamoRequestDTO;
import cl.bibliotecaProyecto.prestamo.dto.PrestamoResponseDTO;
import cl.bibliotecaProyecto.prestamo.dto.StockDTO;
import cl.bibliotecaProyecto.prestamo.dto.UsuarioDTO;
import cl.bibliotecaProyecto.prestamo.model.Prestamo;
import cl.bibliotecaProyecto.prestamo.repository.PrestamoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final WebClient stockWebClient;
    private final WebClient usuarioWebClient;

    private PrestamoResponseDTO mapToDTO(Prestamo prestamo) {
        UsuarioDTO usuario = usuarioWebClient.get()
                .uri("/{id}", prestamo.getIdUsuario())
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();

        StockDTO stock = stockWebClient.get()
                .uri("/{id}", prestamo.getIdStock())
                .retrieve()
                .bodyToMono(StockDTO.class)
                .block();

        return new PrestamoResponseDTO(
                prestamo.getIdPrestamo(),
                prestamo.getFechaInicio(),
                prestamo.getFechaLimite(),
                prestamo.getEstadoPrestamo(),
                usuario != null ? usuario.getNombreUsuario() : "Usuario no disponible",
                stock != null ? stock.getIdStock() : null
        );
    }

    public List<PrestamoResponseDTO> obtenerTodos() {
        return prestamoRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<PrestamoResponseDTO> obtenerPorId(Long id){
        return prestamoRepository.findById(id).map(this::mapToDTO);
    }

    public List<PrestamoResponseDTO> buscarPorIdUsuario(Long idUsuario) {
        return prestamoRepository.findByIdUsuario(idUsuario)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PrestamoResponseDTO guardar(PrestamoRequestDTO dto){
        Prestamo prestamo = new Prestamo(
                null,
                dto.getFechaInicio(),
                dto.getFechaLimite(),
                dto.getEstadoPrestamo(),
                dto.getIdUsuario(),
                dto.getIdStock()
        );
        return  mapToDTO(prestamoRepository.save(prestamo));
    }

    public Optional<PrestamoResponseDTO> actualizar (Long id, PrestamoRequestDTO dto){
        return prestamoRepository.findById(id).map(existente ->
        {
            existente.setFechaInicio(dto.getFechaInicio());
            existente.setFechaLimite(dto.getFechaLimite());
            existente.setEstadoPrestamo(dto.getEstadoPrestamo());
            existente.setIdUsuario(dto.getIdUsuario());
            existente.setIdStock(dto.getIdStock());
            return mapToDTO(prestamoRepository.save(existente));
        });
    }

    public void eliminar(Long id){prestamoRepository.deleteById(id);}
}







