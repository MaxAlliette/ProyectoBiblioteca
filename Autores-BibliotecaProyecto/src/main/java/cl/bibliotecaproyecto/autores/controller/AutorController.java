package cl.bibliotecaproyecto.autores.controller;

import cl.bibliotecaproyecto.autores.dto.AutorRequestDTO;
import cl.bibliotecaproyecto.autores.dto.AutorResponseDTO;
import cl.bibliotecaproyecto.autores.model.Autor;
import cl.bibliotecaproyecto.autores.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/autores")
@RequiredArgsConstructor
public class AutorController {
    private final AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(autorService.obtenerTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("nombreapellido")
    public ResponseEntity<List<AutorResponseDTO>> buscarPorNombreAutoryApellidoAutor(
            @RequestParam String nombreAutor,  @RequestParam String apellidoAutor) {
        return ResponseEntity.ok(autorService.buscarPorNombreAndApellido(nombreAutor, apellidoAutor));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDTO> crear(
            @Valid @RequestBody AutorRequestDTO dto) {
        return ResponseEntity.status(201).body(autorService.guardar(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<AutorResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AutorRequestDTO dto){
        return autorService.actualizar(id,dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (autorService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        autorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}