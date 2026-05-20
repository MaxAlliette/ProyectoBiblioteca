package cl.bibliotecaproyecto.multa.controller;


import cl.bibliotecaproyecto.multa.dto.MultaRequestDTO;
import cl.bibliotecaproyecto.multa.dto.MultaResponseDTO;
import cl.bibliotecaproyecto.multa.repository.MultaRepository;
import cl.bibliotecaproyecto.multa.service.MultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/multas")
@RequiredArgsConstructor
public class MultaController {
    private final MultaService multaService;

    @GetMapping
    public ResponseEntity<List<MultaResponseDTO>> obtenerTodas(){
        return ResponseEntity.ok(multaService.obtenerTodas());
    }

    @GetMapping("{id}")
    public ResponseEntity<MultaResponseDTO> obtenerPorId(@PathVariable Long id){
        return multaService.obtenerMultaPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MultaResponseDTO> guardarMulta(@Valid @RequestBody MultaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(multaService.guardarMulta(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MultaResponseDTO> actualizarMulta(@PathVariable Long id, @Valid @RequestBody MultaRequestDTO dto) {
        return multaService.actualizarMulta(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMulta (@PathVariable Long id){
        multaService.eliminarMulta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MultaResponseDTO>> buscarPorNombreDeUsuario(@RequestParam String nombre) {
        List<MultaResponseDTO> resultados = multaService.buscarPorNombreDeUsuario(nombre);
        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resultados);
    }
}
