package cl.bibliotecaproyecto.rol.controller;

import cl.bibliotecaproyecto.rol.dto.RolRequestDTO;
import cl.bibliotecaproyecto.rol.dto.RolResponseDTO;
import cl.bibliotecaproyecto.rol.model.Rol;
import cl.bibliotecaproyecto.rol.service.RolService;
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

    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(rolService.obtenerTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<RolResponseDTO> obtenerPorId(@PathVariable Long id) {
        return rolService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RolResponseDTO> guardarNuevoRol(@RequestBody @Valid RolRequestDTO dto) {
        return ResponseEntity.status(201).body(rolService.guardar(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (rolService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        rolService.eliminar(id);
        return ResponseEntity.noContent().build(); //204
    }
}
