package cl.bibliotecaproyecto.libros.controller;

import cl.bibliotecaproyecto.libros.dto.LibroRequestDTO;
import cl.bibliotecaproyecto.libros.dto.LibroResponseDTO;
import cl.bibliotecaproyecto.libros.model.Libro;
import cl.bibliotecaproyecto.libros.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Libros", description = "Interaccion con Libros")
@RestController
@RequestMapping("/api/v1/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;



    @Operation(summary = "Listar Libros", description = "Retorna todos los libros registrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping
    public ResponseEntity<List<LibroResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(libroService.obtenerTodos());
    }




    @Operation(summary = "Buscar por ID", description = "Retorna el libro asociado a ese ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID invalido"),
            @ApiResponse(responseCode = "404", description = "Libro no Encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("{id}")
    public ResponseEntity<LibroResponseDTO> obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @Operation(summary = "Crear Libro")
    @ApiResponse(responseCode = "201", description = "Creado")
    @ApiResponse(responseCode = "400", description = "Datos proporcionados invalidos")
    @PostMapping
    public ResponseEntity<LibroResponseDTO> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del Libro")
            @Valid @RequestBody LibroRequestDTO dto){
        return ResponseEntity.status(201).body(libroService.guardar(dto));
    }





    @Operation(summary = "Actualizar un Libro", description = "Actualizar el libro mediante su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "404", description = "Libro no Encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @PutMapping("{id}")
    public ResponseEntity<LibroResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody LibroRequestDTO dto){
        return libroService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }





    @Operation(summary = "Eliminar un Libro", description = "Elimina el libro asociado a ese ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Libro no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (libroService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        libroService.eliminar(id);
        return ResponseEntity.noContent().build(); //204
    }




    @Operation(summary = "Buscar por Titulo", description = "Retorna el libro asociado a ese Titulo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Titulo invalido"),
            @ApiResponse(responseCode = "404", description = "Titulo no Encontrado"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<LibroResponseDTO>> buscarPorTitulo(
            @RequestParam String titulo) {
        return ResponseEntity.ok(libroService.buscarPorTitulo(titulo));
    }





    @Operation(summary = "Buscar por Categoria", description = "Retorna el libro asociado a esa Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Categoria invalida"),
            @ApiResponse(responseCode = "404", description = "Categoria no Encontrada"),
            @ApiResponse(responseCode = "500", description = "Error del servidor")
    })
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<LibroResponseDTO>> buscarPorCategoria(
            @PathVariable Long id) {
        return ResponseEntity.ok(libroService.buscarPorCategoria(id));
    }

}
