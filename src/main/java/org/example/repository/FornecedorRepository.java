package org.example.repository;

import org.example.entities.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FornecedorRepository {

    private List<Fornecedor> fornecedores;

    public FornecedorRepository() {
        this.fornecedores = new ArrayList<>();
    }

    /**
     * Adiciona um novo fornecedor.
     * @throws IllegalArgumentException se o fornecedor for inválido
     */
    public void adicionar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor não pode ser nulo.");
        }
        validarFornecedor(fornecedor);

        // Verifica se já existe um fornecedor com esse forId
        if (buscarPorId(fornecedor.getForId()).isPresent()) {
            throw new IllegalArgumentException("Já existe um fornecedor com o ID: " + fornecedor.getForId());
        }

        fornecedores.add(fornecedor);
    }

    /**
     * Atualiza um fornecedor existente.
     * @return true se atualizado, false se não encontrado
     */
    public boolean atualizar(Fornecedor fornecedorAtualizado) {
        if (fornecedorAtualizado == null) {
            throw new IllegalArgumentException("Fornecedor não pode ser nulo.");
        }
        validarFornecedor(fornecedorAtualizado);

        for (int i = 0; i < fornecedores.size(); i++) {
            Fornecedor fornecedor = fornecedores.get(i);
            if (fornecedor.getForId().equals(fornecedorAtualizado.getForId())) {
                fornecedores.set(i, fornecedorAtualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um fornecedor pelo ID.
     * @return true se removido, false se não encontrado
     */
    public boolean remover(String forId) {
        if (forId == null || forId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do fornecedor não pode ser nulo ou vazio.");
        }
        return fornecedores.removeIf(f -> f.getForId().equals(forId));
    }

    /**
     * Busca um fornecedor pelo ID.
     * @return Optional com o fornecedor, ou vazio se não encontrado
     */
    public Optional<Fornecedor> buscarPorId(String forId) {
        return fornecedores.stream()
                .filter(f -> f.getForId().equals(forId))
                .findFirst();
    }

    /**
     * Lista todos os fornecedores.
     */
    public List<Fornecedor> listarTodos() {
        return new ArrayList<>(fornecedores);
    }

    /**
     * Lista apenas os fornecedores ativos.
     */
    public List<Fornecedor> listarAtivos() {
        return fornecedores.stream()
                .filter(Fornecedor::isForSAtivo)
                .toList();
    }

    /**
     * Valida campos obrigatórios de um fornecedor.
     */
    private void validarFornecedor(Fornecedor fornecedor) {
        if (fornecedor.getForId() == null || fornecedor.getForId().trim().isEmpty()) {
            throw new IllegalArgumentException("ID do fornecedor é obrigatório.");
        }
        if (fornecedor.getForRazaoSocial() == null || fornecedor.getForRazaoSocial().trim().isEmpty()) {
            throw new IllegalArgumentException("Razão social é obrigatória.");
        }
        if (fornecedor.getForCnpj() == null || fornecedor.getForCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ é obrigatório.");
        }
        // Validação simples de CNPJ (14 dígitos)
        if (!fornecedor.getForCnpj().matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ deve ter 14 dígitos.");
        }
    }
}