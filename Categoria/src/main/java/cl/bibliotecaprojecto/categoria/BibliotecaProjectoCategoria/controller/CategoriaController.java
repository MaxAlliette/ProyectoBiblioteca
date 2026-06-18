package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.controller;

import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto.CategoriaRequestDTO;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto.CategoriaResponseDTO;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.model.Categoria;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.service.CategoriaService;
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

@Tag(name = "Categorias", description = "Catalogo de Categoria")
@RestController
@RequestMapping("api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @Operation(summary = "Listar categorias", description = "Retorna todas las categorias registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(categoriaService.obtenerTodos());
    }

    @Operation(summary = "Buscar categoria por ID", description = "Retorna la categoria que le corresponda el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "ID no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar categoria por Nombre", description = "Retorna la categoria que le corresponda el nombre escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Nombre no valido", content = @Content),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @GetMapping("categoria")
    public ResponseEntity<List<CategoriaResponseDTO>> buscarPorNombreCategoria(
            @RequestParam String nombreCategoria) {
        return ResponseEntity.ok(categoriaService.buscarPorNombreCategoria(nombreCategoria));
    }

    @Operation(summary = "Crear categoria", description = "Crea categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Entrada no valida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crear(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Categoria a crear", required = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Categoria.class),
                    examples = @ExampleObject(value = "{ \"nombre_categoria\": \"Nueva categoria\"}")))@RequestBody @Valid CategoriaRequestDTO dto) {
        return ResponseEntity.status(201).body(categoriaService.guardar(dto));
    }

    @Operation(summary = "Actualizar categoria", description = "Actualiza datos de la categoria que le pertenezca el id escrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "400", description = "Entrada no valida", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<CategoriaResponseDTO>  actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO dto){
        return categoriaService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar categoria", description = "Elimina categoria buscado por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No hay contenido. Categoria eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Categoria no encontrada", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build(); //204
    }
}
