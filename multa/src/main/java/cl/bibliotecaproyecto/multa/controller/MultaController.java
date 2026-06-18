package cl.bibliotecaproyecto.multa.controller;


import cl.bibliotecaproyecto.multa.dto.MultaRequestDTO;
import cl.bibliotecaproyecto.multa.dto.MultaResponseDTO;
import cl.bibliotecaproyecto.multa.repository.MultaRepository;
import cl.bibliotecaproyecto.multa.service.MultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Multas", description = "Ver las Multas")
@RestController
@RequestMapping("/api/v1/multas")
@RequiredArgsConstructor
public class MultaController {
    private final MultaService multaService;


    @Operation(summary = "Listar Multas", description = "Retorna todas las multas registradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public ResponseEntity<List<MultaResponseDTO>> obtenerTodas(){
        return ResponseEntity.ok(multaService.obtenerTodas());
    }


    @Operation(summary = "Buscar por ID", description = "Retorna la multa asociada a ese ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID invalido"),
            @ApiResponse(responseCode = "404", description = "Multa no Encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("{id}")
    public ResponseEntity<MultaResponseDTO> obtenerPorId(@PathVariable Long id){
        return multaService.obtenerMultaPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }




    @Operation(summary = "Crear Multa")
    @ApiResponse(responseCode = "201", description = "Creado")
    @ApiResponse(responseCode = "400", description = "Datos proporcionados invalidos")
    @PostMapping
    public ResponseEntity<MultaResponseDTO> guardarMulta(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos de la multa")
            @Valid @RequestBody MultaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(multaService.guardarMulta(dto));
    }


    @Operation(summary = "Actualizar una multa", description = "Actualizar la multa mediante su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "404", description = "Multa no Encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MultaResponseDTO> actualizarMulta(@PathVariable Long id, @Valid @RequestBody MultaRequestDTO dto) {
        return multaService.actualizarMulta(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }



    @Operation(summary = "Eliminar una multa", description = "Elimina la multa asociada a ese ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Multa no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMulta (@PathVariable Long id){
        multaService.eliminarMulta(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Buscar por Nombre de Usuario", description = "Retorna la multa asociada a ese Usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Nombre de usuario invalido"),
            @ApiResponse(responseCode = "404", description = "Multa no Encontrada"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<MultaResponseDTO>> buscarPorNombreDeUsuario(@RequestParam String nombre) {
        List<MultaResponseDTO> resultados = multaService.buscarPorNombreDeUsuario(nombre);
        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultados);
    }
}
