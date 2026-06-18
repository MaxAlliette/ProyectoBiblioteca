package cl.bibliotecaproyecto.autores.controller;

import cl.bibliotecaproyecto.autores.dto.AutorRequestDTO;
import cl.bibliotecaproyecto.autores.dto.AutorResponseDTO;
import cl.bibliotecaproyecto.autores.model.Autor;
import cl.bibliotecaproyecto.autores.service.AutorService;
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

@Tag(name = "Autores", description = "Catálogo de Autores")
@RestController
@RequestMapping("api/v1/autores")
@RequiredArgsConstructor
public class AutorController {
    private final AutorService autorService;

    @Operation(summary = "Listar autores", description = "Retorna todos los autores registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(autorService.obtenerTodos());
    }

    @Operation(summary = "Buscar autor por id", description = "Retorna el autor que le corresponda el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<AutorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar autor por nombre y apellido", description = "Retorna datos del autor que se busca por su nombre y apellido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Nombre y apellido no validos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("nombreapellido")
    public ResponseEntity<List<AutorResponseDTO>> buscarPorNombreAutoryApellidoAutor(
            @RequestParam String nombreAutor,  @RequestParam String apellidoAutor) {
        return ResponseEntity.ok(autorService.buscarPorNombreAndApellido(nombreAutor, apellidoAutor));
    }

    @Operation(summary = "Crear autor", description = "Crea autor nuevo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creado"),
            @ApiResponse(responseCode = "400", description = "Entrada no valida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AutorResponseDTO> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del autor")
            @Valid @RequestBody AutorRequestDTO dto) {
        return ResponseEntity.status(201).body(autorService.guardar(dto));
    }

    @Operation(summary = "Actualizar autor", description = "Actualiza datos del autor que le pertenezca el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID no valido", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<AutorResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AutorRequestDTO dto){
        return autorService.actualizar(id,dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar autor", description = "Elimina autor buscado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No hay contenido. Autor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (autorService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        autorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}