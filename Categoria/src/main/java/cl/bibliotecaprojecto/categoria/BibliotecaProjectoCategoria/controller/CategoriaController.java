package cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.controller;

import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto.CategoriaRequestDTO;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.dto.CategoriaResponseDTO;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.model.Categoria;
import cl.bibliotecaprojecto.categoria.BibliotecaProjectoCategoria.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(categoriaService.obtenerTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("categoria")
    public ResponseEntity<List<CategoriaResponseDTO>> buscarPorNombreCategoria(
            @RequestParam String nombreCategoria) {
        return ResponseEntity.ok(categoriaService.buscarPorNombreCategoria(nombreCategoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> guardarNuevoRol(@RequestBody @Valid CategoriaRequestDTO dto) {
        return ResponseEntity.status(201).body(categoriaService.guardar(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaResponseDTO>  actualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequestDTO dto){
        return categoriaService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (categoriaService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build(); //204
    }
}
