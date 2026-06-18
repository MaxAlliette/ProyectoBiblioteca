package cl.bibliotecaproyecto.stock.stock.controller;

import cl.bibliotecaproyecto.stock.stock.dto.StockRequestDTO;
import cl.bibliotecaproyecto.stock.stock.dto.StockResponseDTO;
import cl.bibliotecaproyecto.stock.stock.model.Stock;
import cl.bibliotecaproyecto.stock.stock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Stocks", description = "Catalogo de Stock")
@RestController
@RequestMapping("/api/v1/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @Operation(summary = "Listar Stocks", description = "Retorna todos las stocks registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<StockResponseDTO>> obtenerTodas(){
        return ResponseEntity.ok(stockService.obtenerTodos());
    }

    @Operation(summary = "Buscar stock por ID", description = "Retorna el stock que le corresponda el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Stock no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<StockResponseDTO> obtenerPorId(@PathVariable Long id){
        return stockService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear stock", description = "Crea stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Entrada no valida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<StockResponseDTO> crear(
            @Valid @RequestBody StockRequestDTO dto){
        return ResponseEntity.status(201).body(stockService.guardar(dto));
    }

    @Operation(summary = "Actualizar stock", description = "Actualiza datos de el stock que le pertenezca el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Entrada no valida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<StockResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody StockRequestDTO dto){
        return stockService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar Stock", description = "Elimina stock buscado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No hay contenido. Stock eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Stock no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (stockService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        stockService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar stock por nombre de libro", description = "Retorna el stock que le corresponda el nombre del libro escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Nombre no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Stock no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("/Libro/{id}")
    public ResponseEntity<List<StockResponseDTO>> buscarPorIdLibro(
            @PathVariable Long id){
        return ResponseEntity.ok(stockService.buscarPorIdLibro(id));
    }

}
