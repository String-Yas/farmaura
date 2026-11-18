package br.com.farmaura.controller;

import br.com.farmaura.model.Produto;
import br.com.farmaura.service.ProdutoService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @PathVariable String id) {

        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FARMACEUTICO')")
    public ResponseEntity<?> criar(@RequestBody Produto produto) {
        if (produto.getIdProduto() == null || produto.getIdProduto().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "O ID do produto é obrigatório."));
        }
        if (produtoService.buscarPorId(produto.getIdProduto()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Já existe um produto com o ID: " + produto.getIdProduto()));
        }
        return ResponseEntity.ok(produtoService.salvar(produto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FARMACEUTICO')")
    public ResponseEntity<?> editar(
            @PathVariable String id,
            @RequestBody Produto produto) {

        return produtoService.buscarPorId(id)
                .map(existente -> {
                    produto.setIdProduto(id);
                    return ResponseEntity.ok(produtoService.salvar(produto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FARMACEUTICO')")
    public ResponseEntity<Void> deletar(
            @PathVariable String id) {

        if (produtoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
