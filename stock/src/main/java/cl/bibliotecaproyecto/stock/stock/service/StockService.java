package cl.bibliotecaproyecto.stock.stock.service;

import cl.bibliotecaproyecto.stock.stock.dto.LibroDTO;
import cl.bibliotecaproyecto.stock.stock.dto.StockRequestDTO;
import cl.bibliotecaproyecto.stock.stock.dto.StockResponseDTO;
import cl.bibliotecaproyecto.stock.stock.model.Stock;
import cl.bibliotecaproyecto.stock.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final WebClient webClient;

    private StockResponseDTO mapToDTO(Stock stock) {
        LibroDTO libro = webClient.get()
                .uri("/{id}", stock.getIdLibro())
                .retrieve()
                .bodyToMono(LibroDTO.class)
                .block();

        return new StockResponseDTO(
                stock.getIdStock(),
                stock.getCantidadStock(),
                stock.getEstado_ejemplar(),
                libro != null ? libro.getTituloLibro() : "Libro no disponible"
        );
    }

    public List<StockResponseDTO> obtenerTodos(){
        return stockRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StockResponseDTO> obtenerPorId(Long id){return stockRepository.findById(id).map(this::mapToDTO);}

    public StockResponseDTO guardar(StockRequestDTO dto){
        Stock stock = new Stock(
                null,
                dto.getCantidad(),
                dto.getEstado_ejemplar(),
                dto.getLibroId()
        );
        return mapToDTO(stockRepository.save(stock));
    }

    public Optional<StockResponseDTO> actualizar(Long id, StockRequestDTO dto){
        return stockRepository.findById(id).map(existente ->
        {
            existente.setCantidadStock(dto.getCantidad());
            existente.setEstado_ejemplar(dto.getEstado_ejemplar());
            existente.setIdLibro(dto.getLibroId());
            return mapToDTO(stockRepository.save(existente));
        });
    }

    public void eliminar(Long id){stockRepository.deleteById(id);}

    public List<StockResponseDTO> buscarPorIdLibro(Long idLibro){
        return stockRepository.findByIdLibro(idLibro)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
