package org.example.controller;

import org.example.entities.Fornecedor;
import org.example.entities.Fornecedor;
import org.example.repository.FornecedorRepository;
import org.example.services.FornecedorService;
import org.example.services.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedors")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    /**
     * GET /fornecedors - Lista todos os fornecedors.
     */
    @GetMapping
    public ResponseEntity<List<Fornecedor>> listar() {
        List<Fornecedor> fornecedors = service.listarTodos();
        return ResponseEntity.ok(fornecedors);
    }

    /**
     * GET /fornecedors/{id} - Busca um fornecedor pelo cliId.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable String id) {
        Optional<Fornecedor> fornecedor = service.buscarPorId(id);
        return fornecedor
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /fornecedors - Cria um novo fornecedor.
     */
    @PostMapping
    public ResponseEntity<Fornecedor> salvar(@RequestBody Fornecedor fornecedor) {
        try {
            service.salvar(fornecedor);
            URI location = URI.create("/fornecedors/" + fornecedor.getForId());
            return ResponseEntity.created(location).body(fornecedor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * PUT /fornecedors/{id} - Atualiza um fornecedor existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable String id, @RequestBody Fornecedor fornecedorAtualizado) {
        if (!id.equals(fornecedorAtualizado.getForId())) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            boolean atualizado = service.atualizar(fornecedorAtualizado);
            if (atualizado) {
                return ResponseEntity.ok(fornecedorAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * DELETE /fornecedors/{id} - Remove um fornecedor.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        try {
            boolean removido = service.remover(id);
            if (removido) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}