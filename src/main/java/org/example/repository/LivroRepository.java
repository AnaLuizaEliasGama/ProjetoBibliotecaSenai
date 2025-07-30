package org.example.repository;

import org.example.entities.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class LivroRepository {

    private List<Livro> livros;

    public LivroRepository() {
        this.livros = new ArrayList<>();
    }

    /**
     * Adiciona um novo livro ao repositório.
     */
    public void adicionar(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo.");
        }
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode estar vazio.");
        }
        livros.add(livro);
    }

    /**
     * Atualiza as informações de um livro existente.
     * @return true se atualizado, false se não encontrado.
     */
    public boolean atualizar(Livro livroAtualizado) {
        for (Livro livro : livros) {
            if (livro.getId().equals(livroAtualizado.getId())) {
                livro.setTitulo(livroAtualizado.getTitulo());
                livro.setIsbn(livroAtualizado.getIsbn());
                livro.setEditora(livroAtualizado.getEditora());
                livro.setAutor(livroAtualizado.getAutor());
                livro.setEdicao(livroAtualizado.getEdicao());
                livro.setIdioma(livroAtualizado.getIdioma());
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um livro pelo ID.
     * @return true se removido, false se não encontrado.
     */
    public boolean remover(String id) {
        return livros.removeIf(livro -> livro.getId().equals(id));
    }

    /**
     * Busca um livro pelo ID.
     * @return Optional com o livro, ou vazio se não encontrado.
     */
    public Optional<Livro> buscarPorId(String id) {
        return livros.stream()
                .filter(livro -> livro.getId().equals(id))
                .findFirst();
    }

    /**
     * Retorna todos os livros.
     */
    public List<Livro> listarTodos() {
        return new ArrayList<>(livros);
    }

    /**
     * Retorna todos os livros disponíveis.
     */
    public List<Livro> listarDisponiveis() {
        return new ArrayList<>(livros); // futuramente pode filtrar por .isDisponivel()
    }

    /**
     * Busca um livro pelo ISBN.
     * @return Optional com o livro, ou vazio se não encontrado.
     */
    public Optional<Livro> buscarPorIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return Optional.empty();
        }
        return livros.stream()
                .filter(livro -> livro.getIsbn().equals(isbn))
                .findFirst();
    }
}