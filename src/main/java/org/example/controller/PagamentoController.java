package org.example.controller;

import org.example.entities.Pagamento;
import org.example.services.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciar pagamentos.
 * Endpoints: GET, POST, PUT, DELETE em /pagamentos
 */
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    /**
     * GET /pagamentos - Lista todos os pagamentos.
     */
    @GetMapping
    public ResponseEntity<List<Pagamento>> listar() {
        List<Pagamento> pagamentos = service.listarTodos();
        return ResponseEntity.ok(pagamentos);
    }

    /**
     * GET /pagamentos/{id} - Busca um pagamento pelo cliId.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable String id) {
        Optional<Pagamento> pagamento = service.buscarPorId(Integer.parseInt(id));
        return pagamento
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /pagamentos - Cria um novo pagamento.
     */
    @PostMapping
    public ResponseEntity<Pagamento> salvar(@RequestBody Pagamento pagamento) {
        try {
            service.salvar(pagamento);
            URI location = URI.create("/pagamentos/" + pagamento.getId());
            return ResponseEntity.created(location).body(pagamento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * PUT /pagamentos/{id} - Atualiza um pagamento existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(@PathVariable String id, @RequestBody Pagamento pagamentoAtualizado) {
        if (!id.equals(pagamentoAtualizado.getId())) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            boolean atualizado = service.atualizar(pagamentoAtualizado);
            if (atualizado) {
                return ResponseEntity.ok(pagamentoAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * DELETE /pagamentos/{id} - Remove um pagamento.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        try {
            boolean removido = service.remover(Integer.parseInt(id));
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