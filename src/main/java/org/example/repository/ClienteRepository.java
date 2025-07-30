package org.example.repository;

import org.example.entities.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository

/**
 * Repositório para gerenciar clientes.
 * Não herda de Cliente! Apenas usa a classe Cliente.
 */
public class ClienteRepository {

    private List<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }

    /**
     * Adiciona um novo cliente ao repositório.
     */
    public void adicionar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        if (cliente.getCliId() == null || cliente.getCliId().trim().isEmpty()) {
            throw new IllegalArgumentException("cliId não pode ser vazio.");
        }
        if (cliente.getCliNome() == null || cliente.getCliNome().trim().isEmpty()) {
            throw new IllegalArgumentException("cliNome não pode ser vazio.");
        }
        if (cliente.getCliCpf() == null || cliente.getCliCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("cliCpf não pode ser vazio.");
        }

        // Evita duplicar ID
        if (buscarPorCliId(cliente.getCliId()).isPresent()) {
            throw new IllegalArgumentException("Já existe um cliente com cliId: " + cliente.getCliId());
        }

        clientes.add(cliente);
    }

    /**
     * Atualiza um cliente existente pelo cliId.
     * @return true se atualizado, false se não encontrado
     */
    public boolean atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.getCliId().equals(clienteAtualizado.getCliId())) {
                clientes.set(i, clienteAtualizado);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um cliente pelo cliId.
     * @return true se removido, false se não encontrado
     */
    public boolean remover(String cliId) {
        return clientes.removeIf(cliente -> cliente.getCliId().equals(cliId));
    }

    /**
     * Busca um cliente pelo cliId.
     */
    public Optional<Cliente> buscarPorCliId(String cliId) {
        return clientes.stream()
                .filter(c -> c.getCliId().equals(cliId))
                .findFirst();
    }

    /**
     * Lista todos os clientes.
     */
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }
}