package cl.bibliotecaproyecto.reservas.controller;

import cl.bibliotecaproyecto.reservas.dto.ReservaRequestDTO;
import cl.bibliotecaproyecto.reservas.dto.ReservaResponseDTO;
import cl.bibliotecaproyecto.reservas.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService  reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> obtenerTodos(){
        return ResponseEntity.ok(reservaService.obtenerTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return reservaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crearReserva(@Valid @RequestBody ReservaRequestDTO dto){
        return ResponseEntity.status(201).body(reservaService.guardar(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ReservaResponseDTO>  actualizarReserva(
            @PathVariable Long id,
            @Valid @RequestBody ReservaRequestDTO dto){
        return reservaService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id){
        if (reservaService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Sala/{id}")
    public ResponseEntity<List<ReservaResponseDTO>> buscarPorIdSala(
            @PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorIdSala(id));
    }

    @GetMapping("/Usuario/{id}")
    public ResponseEntity<List<ReservaResponseDTO>> buscarPorIdUsuario(
            @PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorIdUsuario(id));
    }
}
