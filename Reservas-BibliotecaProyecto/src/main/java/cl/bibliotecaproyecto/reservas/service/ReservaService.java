package cl.bibliotecaproyecto.reservas.service;

import cl.bibliotecaproyecto.reservas.dto.ReservaRequestDTO;
import cl.bibliotecaproyecto.reservas.dto.ReservaResponseDTO;
import cl.bibliotecaproyecto.reservas.dto.SalaDTO;
import cl.bibliotecaproyecto.reservas.dto.UsuarioDTO;
import cl.bibliotecaproyecto.reservas.model.Reserva;
import cl.bibliotecaproyecto.reservas.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;
    private final WebClient usuarioWebClient;
    private final WebClient salaWebClient;

    private ReservaResponseDTO mapToDTO(Reserva reserva){
        UsuarioDTO usuario = usuarioWebClient.get()
                .uri("/{id}", reserva.getIdUsuario())
                .retrieve()
                .bodyToMono(UsuarioDTO.class)
                .block();

        SalaDTO sala = salaWebClient.get()
                .uri("/{id}", reserva.getIdSala())
                .retrieve()
                .bodyToMono(SalaDTO.class)
                .block();

        return new ReservaResponseDTO(
                reserva.getIdReserva(),
                reserva.getFechaReserva(),
                reserva.getHoraInicioReserva(),
                reserva.getHoraFinReserva(),
                usuario != null ? usuario.getNombreUsuario() : "Usuario no disponible",
                sala != null ? sala.getNombreSala() : "Sala no disponible"
        );
    }

    public List<ReservaResponseDTO> obtenerTodos(){
        return reservaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReservaResponseDTO> obtenerPorId(Long id){
        return reservaRepository.findById(id).map(this::mapToDTO);
    }

    public ReservaResponseDTO guardar(ReservaRequestDTO dto){
        Reserva reserva = new Reserva(
                null,
                dto.getFechaReserva(),
                dto.getHoraInicioReserva(),
                dto.getHoraFinReserva(),
                dto.getIdUsuario(),
                dto.getIdSala()
        );
        return mapToDTO(reservaRepository.save(reserva));
    }

    public Optional<ReservaResponseDTO> actualizar (Long id, ReservaRequestDTO dto){
        return reservaRepository.findById(id).map(existente ->
        {
            existente.setFechaReserva(dto.getFechaReserva());
            existente.setHoraInicioReserva(dto.getHoraInicioReserva());
            existente.setHoraFinReserva(dto.getHoraFinReserva());
            existente.setIdUsuario(dto.getIdUsuario());
            existente.setIdSala(dto.getIdSala());
            return mapToDTO(reservaRepository.save(existente));
        });
    }

    public void eliminar(Long id){reservaRepository.deleteById(id);}

    public List<ReservaResponseDTO> buscarPorIdSala(Long idSala) {
        return reservaRepository.findByIdSala(idSala)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<ReservaResponseDTO> buscarPorIdUsuario(Long idUsuario) {
        return reservaRepository.findByIdUsuario(idUsuario)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
