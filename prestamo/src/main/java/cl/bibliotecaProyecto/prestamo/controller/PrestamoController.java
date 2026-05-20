package cl.bibliotecaProyecto.prestamo.controller;

import cl.bibliotecaProyecto.prestamo.dto.PrestamoRequestDTO;
import cl.bibliotecaProyecto.prestamo.dto.PrestamoResponseDTO;
import cl.bibliotecaProyecto.prestamo.service.PrestamoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/prestamos")
@RequiredArgsConstructor
public class PrestamoController {
    private final PrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<PrestamoResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(prestamoService.obtenerTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<PrestamoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return prestamoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/Usuario/{id}")
    public ResponseEntity<List<PrestamoResponseDTO>> buscarPorIdUsuario(
            @PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.buscarPorIdUsuario(id));
    }

    @PostMapping
    public ResponseEntity<PrestamoResponseDTO> crearReserva(@Valid @RequestBody PrestamoRequestDTO dto){
        return ResponseEntity.status(201).body(prestamoService.guardar(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<PrestamoResponseDTO>  actualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody PrestamoRequestDTO dto){
        return prestamoService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id){
        if (prestamoService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        prestamoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
