package org.example.services;

import org.example.entities.Livro;
import org.example.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository repository;
    public LivroService(LivroRepository repository){
        this.repository = repository;
        }
        public List<Livro> listarTodos(){
        return repository.listarTodos();
        }

        public Optional<Livro> buscarPorId(String id) {
        return repository.buscarPorId(id);

        }

        public void salvar(Livro livro){
        if (livro.getTitulo().length() < 3) {
            throw new IllegalArgumentException("Título curto demais");
                        }
        repository.adicionar(livro);
        }

        public boolean atualizar(Livro livro){
        return repository.atualizar(livro);

        }

        public boolean remover(String id) {
            return repository.remover(id);
        }
        }
