package org.example.Resources;

import org.example.entities.Cliente;
import org.example.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciar clientes.
 * Endpoints: GET, POST, PUT, DELETE em /clientes
 */
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    /**
     * GET /clientes - Lista todos os clientes.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> clientes = service.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    /**
     * GET /clientes/{id} - Busca um cliente pelo cliId.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable String id) {
        Optional<Cliente> cliente = service.buscarPorId(id);
        return cliente
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /clientes - Cria um novo cliente.
     */
    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        try {
            service.salvar(cliente);
            URI location = URI.create("/clientes/" + cliente.getCliId());
            return ResponseEntity.created(location).body(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * PUT /clientes/{id} - Atualiza um cliente existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable String id, @RequestBody Cliente clienteAtualizado) {
        if (!id.equals(clienteAtualizado.getCliId())) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            boolean atualizado = service.atualizar(clienteAtualizado);
            if (atualizado) {
                return ResponseEntity.ok(clienteAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * DELETE /clientes/{id} - Remove um cliente.
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