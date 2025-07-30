package org.example.controller; // ✅ Correção: resources (minúsculo)

import org.example.entities.Livro; // ✅ Import necessário
import org.example.services.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // ✅ Import para @DeleteMapping

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    /**
     * GET /livro - Lista todos os livros.
     */
    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        List<Livro> livros = service.listarTodos();
        return ResponseEntity.ok(livros);
    }

    /**
     * GET /livro/{id} - Busca um livro pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable String id) {
        Optional<Livro> livro = service.buscarPorId(id);
        return livro
                .map(ResponseEntity::ok) // ✅ Correção: ::ok em vez de apenas ResponseEntity
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /livro/isbn/{isbn} - Busca um livro pelo ISBN.
     */
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Livro> buscarPorIsbn(@PathVariable String isbn) {
        Optional<Livro> livro = service.buscarPorIsbn(isbn); // ✅ Precisa implementar no service
        return livro
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE /livro/{id} - Remove (desativa) um livro.
     * Observação: Não exclui fisicamente, apenas marca como inativo.
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