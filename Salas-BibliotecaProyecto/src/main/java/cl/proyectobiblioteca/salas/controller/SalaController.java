package cl.proyectobiblioteca.salas.controller;

import cl.proyectobiblioteca.salas.dto.SalaRequestDTO;
import cl.proyectobiblioteca.salas.dto.SalaResponseDTO;
import cl.proyectobiblioteca.salas.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Salas", description = "Catálogo de Salas")
@RestController
@RequestMapping("api/v1/salas")
@RequiredArgsConstructor
public class SalaController {
    private final SalaService salaService;
    @Operation(summary = "Listar Salas", description = "Retorna todas las salas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(salaService.obtenerTodos());
    }

    @Operation(summary = "Buscar sala por id", description = "Retorna la sala que le corresponda el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("{id}")
    public ResponseEntity<SalaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return salaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear sala", description = "Crea sala nueva")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creada"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PostMapping
    public ResponseEntity<SalaResponseDTO> crear(
            @Valid @RequestBody SalaRequestDTO dto) {
        return ResponseEntity.status(201).body(salaService.guardar(dto));
    }

    @Operation(summary = "Actualizar sala", description = "Actualiza datos de la sala que le pertenezca el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PutMapping("{id}")
    public ResponseEntity<SalaResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody SalaRequestDTO dto){
        return salaService.actualizar(id,dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar sala", description = "Elimina sala buscada por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No hay contenido. Sala eliminada correctamente"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (salaService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        salaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar sala por nombre", description = "Retorna datos del autor que se busca por su nombre y apellido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("nombre")
    public ResponseEntity<List<SalaResponseDTO>> buscarPorNombre(@RequestParam String nombreSala) {
        if (salaService.buscarPorNombreSala(nombreSala).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salaService.buscarPorNombreSala(nombreSala));
    }
}
