package cl.bibliotecaproyecto.libros.controller;

import cl.bibliotecaproyecto.libros.dto.LibroRequestDTO;
import cl.bibliotecaproyecto.libros.dto.LibroResponseDTO;
import cl.bibliotecaproyecto.libros.service.LibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;


    @GetMapping
    public ResponseEntity<List<LibroResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(libroService.obtenerTodos());
    }


    @GetMapping("{id}")
    public ResponseEntity<LibroResponseDTO> obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<LibroResponseDTO> crear(
            @Valid @RequestBody LibroRequestDTO dto){
        return ResponseEntity.status(201).body(libroService.guardar(dto));
    }


    @PutMapping("{id}")
    public ResponseEntity<LibroResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody LibroRequestDTO dto){
        return libroService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (libroService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        libroService.eliminar(id);
        return ResponseEntity.noContent().build(); //204
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<LibroResponseDTO>> buscarPorTitulo(
            @RequestParam String titulo) {
        return ResponseEntity.ok(libroService.buscarPorTitulo(titulo));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<LibroResponseDTO>> buscarPorCategoria(
            @PathVariable Long id) {
        return ResponseEntity.ok(libroService.buscarPorCategoria(id));
    }

}
