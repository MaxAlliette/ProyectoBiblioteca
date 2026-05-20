package cl.bibliotecaproyecto.stock.stock.controller;

import cl.bibliotecaproyecto.stock.stock.dto.StockRequestDTO;
import cl.bibliotecaproyecto.stock.stock.dto.StockResponseDTO;
import cl.bibliotecaproyecto.stock.stock.model.Stock;
import cl.bibliotecaproyecto.stock.stock.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> obtenerTodas(){
        return ResponseEntity.ok(stockService.obtenerTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<StockResponseDTO> obtenerPorId(@PathVariable Long id){
        return stockService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StockResponseDTO> crear(
            @Valid @RequestBody StockRequestDTO dto){
        return ResponseEntity.status(201).body(stockService.guardar(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<StockResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody StockRequestDTO dto){
        return stockService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (stockService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        stockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Libro/{id}")
    public ResponseEntity<List<StockResponseDTO>> buscarPorIdLibro(
            @PathVariable Long id){
        return ResponseEntity.ok(stockService.buscarPorIdLibro(id));
    }

}
