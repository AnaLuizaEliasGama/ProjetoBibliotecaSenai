package org.example.services;

import org.example.entities.Fornecedor;
import org.example.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os fornecedores.
     */
    public List<Fornecedor> listarTodos() {
        return repository.listarTodos();
    }

    /**
     * Busca um fornecedor pelo ID (forId).
     * @return Optional com o fornecedor, ou vazio se não encontrado
     */
    public Optional<Fornecedor> buscarPorId(String forId) {
        return repository.buscarPorId(forId);
    }

    /**
     * Salva um novo fornecedor.
     * @throws IllegalArgumentException se o fornecedor for nulo ou campos obrigatórios estiverem vazios
     */
    public void salvar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor não pode ser nulo.");
        }
        repository.adicionar(fornecedor);
    }

    /**
     * Atualiza um fornecedor existente.
     * @return true se atualizado, false se não encontrado
     */
    public boolean atualizar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor não pode ser nulo.");
        }
        return repository.atualizar(fornecedor);
    }

    /**
     * Remove um fornecedor pelo ID.
     * @return true se removido, false se não encontrado
     */
    public boolean remover(String forId) {
        if (forId == null || forId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do fornecedor não pode ser nulo ou vazio.");
        }
        return repository.remover(forId);
    }

    /**
     * Busca fornecedores por razão social (busca parcial, ignora maiúsculas).
     */
    public List<Fornecedor> buscarPorRazaoSocial(String razaoSocial) {
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
            return List.of();
        }
        return repository.listarTodos().stream()
                .filter(f -> f.getForRazaoSocial().toLowerCase()
                        .contains(razaoSocial.toLowerCase()))
                .toList();
    }

    /**
     * Lista apenas os fornecedores ativos.
     */
    public List<Fornecedor> listarAtivos() {
        return repository.listarAtivos();
    }
}