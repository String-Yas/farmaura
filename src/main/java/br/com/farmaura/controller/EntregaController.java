package br.com.farmaura.controller;

import br.com.farmaura.model.Entrega;
import br.com.farmaura.model.StatusEntrega;
import br.com.farmaura.service.EntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public List<Entrega> listarTodas() {
        return entregaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarPorId(@PathVariable Integer id) {
        return entregaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Entrega criar(@RequestBody Entrega entrega) {
        return entregaService.salvar(entrega);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Entrega> atualizarStatus(@PathVariable Integer id, @RequestParam StatusEntrega status) {
        return ResponseEntity.ok(entregaService.atualizarStatus(id, status));
    }
}
