package cl.bibliotecaproyecto.reservas.controller;

import cl.bibliotecaproyecto.reservas.dto.ReservaRequestDTO;
import cl.bibliotecaproyecto.reservas.dto.ReservaResponseDTO;
import cl.bibliotecaproyecto.reservas.service.ReservaService;
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

@Tag(name = "Reservas", description = "Gestión de reservas")
@RestController
@RequestMapping("api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService  reservaService;
    @Operation(summary = "Listar reservas", description = "Retorna todas las reservas registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(reservaService.obtenerTodos());
    }

    @Operation(summary = "Buscar reserva por id", description = "Retorna la reserva que le corresponda el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<ReservaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return reservaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear reserva", description = "Crea reserva nueva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creado"),
            @ApiResponse(responseCode = "400", description = "Entrada no valida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crearReserva(@Valid @RequestBody ReservaRequestDTO dto){
        return ResponseEntity.status(201).body(reservaService.guardar(dto));
    }

    @Operation(summary = "Actualizar reserva", description = "Actualiza datos de la reserva que le pertenezca el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID no valido", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<ReservaResponseDTO>  actualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody ReservaRequestDTO dto){
        return reservaService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar reserva", description = "Elimina reserva buscada por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No hay contenido. Reserva eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "reserva no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id){
        if (reservaService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar reserva por id de sala", description = "Retorna datos de la reserva que se busca por id de la sala que se reservó")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Id no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("/Sala/{id}")
    public ResponseEntity<List<ReservaResponseDTO>> buscarPorIdSala(
            @PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorIdSala(id));
    }

    @Operation(summary = "Buscar reserva por id de usuario", description = "Retorna datos de la reserva que se busca por id del usuario que reservó")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Id no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("/Usuario/{id}")
    public ResponseEntity<List<ReservaResponseDTO>> buscarPorIdUsuario(
            @PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorIdUsuario(id));
    }
}
