package cl.bibliotecaProyecto.prestamo.controller;

import cl.bibliotecaProyecto.prestamo.dto.PrestamoRequestDTO;
import cl.bibliotecaProyecto.prestamo.dto.PrestamoResponseDTO;
import cl.bibliotecaProyecto.prestamo.service.PrestamoService;
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

@Tag(name = "Prestamos", description = "Catalogo de Prestamos")
@RestController
@RequestMapping("api/v1/prestamos")
@RequiredArgsConstructor
public class PrestamoController {
    private final PrestamoService prestamoService;

    @Operation(summary = "Listar prestamos", description = "Retorna todas los prestamos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PrestamoResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    @Operation(summary = "Buscar prestamo por ID", description = "Retorna el prestamo que le corresponda el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Prestamo no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<PrestamoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return prestamoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar prestamo por ID de usuario", description = "Retorna la prestamo que le corresponda el ID de usuario escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Prestamo no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("/Usuario/{id}")
    public ResponseEntity<List<PrestamoResponseDTO>> buscarPorIdUsuario(
            @PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.buscarPorIdUsuario(id));
    }

    @Operation(summary = "Crear prestamo", description = "Crea prestamo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Entrada no valida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PrestamoResponseDTO> crearReserva(@Valid @RequestBody PrestamoRequestDTO dto){
        return ResponseEntity.status(201).body(prestamoService.guardar(dto));
    }

    @Operation(summary = "Actualizar prestamo", description = "Actualiza datos de el prestamo que le pertenezca el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Entrada no valida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<PrestamoResponseDTO>  actualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody PrestamoRequestDTO dto){
        return prestamoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar prestamo", description = "Elimina prestamo buscado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No hay contenido. Prestamo eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Prestamo no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id){
        if (prestamoService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        prestamoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
