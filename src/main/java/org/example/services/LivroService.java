package org.example.services;

import org.example.entities.Livro;
import org.example.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os livros.
     */
    public List<Livro> listarTodos() {
        return repository.listarTodos();
    }

    /**
     * Busca um livro pelo ID.
     */
    public Optional<Livro> buscarPorId(String id) {
        return repository.buscarPorId(id);
    }

    /**
     * Busca um livro pelo ISBN.
     * @param isbn O ISBN do livro a ser buscado
     * @return Optional com o livro, ou vazio se não encontrado
     */
    public Optional<Livro> buscarPorIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN não pode ser nulo ou vazio");
        }
        return repository.buscarPorIsbn(isbn);
    }

    /**
     * Salva um novo livro com validações.
     */
    public void salvar(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode estar vazio.");
        }
        if (livro.getTitulo().trim().length() < 3) {
            throw new IllegalArgumentException("Título curto demais (mínimo 3 caracteres)");
        }
        repository.adicionar(livro);
    }

    /**
     * Atualiza um livro existente.
     * @return true se atualizado, false se não encontrado
     */
    public boolean atualizar(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        return repository.atualizar(livro);
    }

    /**
     * Remove um livro pelo ID.
     * @return true se removido, false se não encontrado
     */
    public boolean remover(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID não pode ser nulo ou vazio");
        }
        return repository.remover(id);
    }
}