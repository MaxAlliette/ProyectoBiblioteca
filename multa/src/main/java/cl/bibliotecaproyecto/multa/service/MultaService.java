package cl.bibliotecaproyecto.multa.service;

import cl.bibliotecaproyecto.multa.dto.MultaRequestDTO;
import cl.bibliotecaproyecto.multa.dto.MultaResponseDTO;
import cl.bibliotecaproyecto.multa.dto.PrestamoDTO;
import cl.bibliotecaproyecto.multa.model.Multa;
import cl.bibliotecaproyecto.multa.repository.MultaRepository;
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
public class MultaService {

    private  final MultaRepository multaRepository;
    private  final WebClient webClient;

    private MultaResponseDTO mapToDTO(Multa multa){
        PrestamoDTO prestamo = webClient.get()
                .uri("/{id}", multa.getIdPrestamo())
                .retrieve()
                .bodyToMono(PrestamoDTO.class)
                .block();

        return new MultaResponseDTO(
                multa.getIdMulta(),
                multa.getMontoMulta(),
                multa.getEstadoMulta(),
                prestamo != null ? prestamo.getIdPrestamo() : null
        );
    }

    public List<MultaResponseDTO> buscarPorNombreDeUsuario(String nombre){
        List<Long> idPrestamos = webClient.get()
                .uri("/usuarios/{nombre}/prestamos", nombre)
                .retrieve()
                .bodyToFlux(Long.class)
                .collectList()
                .block();
        List<Multa> multas = multaRepository.findByIdPrestamoIn(idPrestamos);
        return multas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<MultaResponseDTO> obtenerTodas(){
        return multaRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<MultaResponseDTO> obtenerMultaPorId(Long id){
        return multaRepository.findById(id).map(this::mapToDTO);
    }

    public MultaResponseDTO guardarMulta(MultaRequestDTO dto){
        Multa multa = new Multa(
                null,
                dto.getMontoMulta(),
                dto.getEstadoMulta(),
                dto.getIdPrestamo()


        );
        return mapToDTO(multaRepository.save(multa));
    }

    public Optional<MultaResponseDTO> actualizarMulta(Long id, MultaRequestDTO dto){
        return multaRepository.findById(id).map(existente ->{
            existente.setMontoMulta(dto.getMontoMulta());
            existente.setEstadoMulta(dto.getEstadoMulta());
            existente.setIdPrestamo(dto.getIdPrestamo());
            return mapToDTO(multaRepository.save(existente));
        });
    }

    public void eliminarMulta(Long id){
        multaRepository.deleteById(id);
    }
}
