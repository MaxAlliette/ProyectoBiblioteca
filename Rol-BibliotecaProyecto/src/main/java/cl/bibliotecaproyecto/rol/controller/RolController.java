package cl.bibliotecaproyecto.rol.controller;

import cl.bibliotecaproyecto.rol.dto.RolRequestDTO;
import cl.bibliotecaproyecto.rol.dto.RolResponseDTO;
import cl.bibliotecaproyecto.rol.model.Rol;
import cl.bibliotecaproyecto.rol.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;


    @Operation(summary = "Listar Roles", description = "Retorna todos los Roles existentes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(rolService.obtenerTodos());
    }


    @Operation(summary = "Buscar por ID", description = "Retorna el rol asociado a ese ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID invalido"),
            @ApiResponse(responseCode = "404", description = "Rol no Encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("{id}")
    public ResponseEntity<RolResponseDTO> obtenerPorId(@PathVariable Long id) {
        return rolService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Guardar nuevo Rol")
    @ApiResponse(responseCode = "201", description = "Creado")
    @ApiResponse(responseCode = "400", description = "Datos proporcionados invalidos")
    @PostMapping
    public ResponseEntity<RolResponseDTO> guardarNuevoRol(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del Libro")
            @RequestBody @Valid RolRequestDTO dto) {
        return ResponseEntity.status(201).body(rolService.guardar(dto));
    }


    @Operation(summary = "Eliminar un Rol", description = "Elimina el rol asociado a ese ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Rol no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (rolService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        rolService.eliminar(id);
        return ResponseEntity.noContent().build(); //204
    }
}
