package cl.proyectobiblioteca.salas.controller;

import cl.proyectobiblioteca.salas.dto.SalaRequestDTO;
import cl.proyectobiblioteca.salas.dto.SalaResponseDTO;
import cl.proyectobiblioteca.salas.service.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/salas")
@RequiredArgsConstructor
public class SalaController {
    private final SalaService salaService;

    @GetMapping
    public ResponseEntity<List<SalaResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(salaService.obtenerTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<SalaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return salaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SalaResponseDTO> crear(
            @Valid @RequestBody SalaRequestDTO dto) {
        return ResponseEntity.status(201).body(salaService.guardar(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<SalaResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody SalaRequestDTO dto){
        return salaService.actualizar(id,dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if (salaService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        salaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("nombre")
    public ResponseEntity<List<SalaResponseDTO>> buscarPorNombre(@RequestParam String nombreSala) {
        if (salaService.buscarPorNombreSala(nombreSala).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(salaService.buscarPorNombreSala(nombreSala));
    }
}
