package org.example.services;

import org.example.entities.Cliente;
import org.example.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;

public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todos os clientes.
     */
    public List<Cliente> listarTodos() {
        return repository.listarTodos();
    }

    /**
     * Busca um cliente pelo cliId.
     */
    public Optional<Cliente> buscarPorId(String id) {
        return repository.buscarPorCliId(id);
    }

    /**
     * Salva um novo cliente com validações.
     */
    public void salvar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        if (cliente.getCliNome() == null || cliente.getCliNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório né?");
        }
        if (cliente.getCliNome().trim().length() < 3) {
            throw new IllegalArgumentException("Mas também você acha que o nome vai ter duas letras? No mínimo três né?!");
        }
        repository.adicionar(cliente);
    }

    /**
     * Atualiza um cliente existente.
     */
    public boolean atualizar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        return repository.atualizar(cliente);
    }

    /**
     * Remove um cliente pelo ID.
     */
    public boolean remover(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Id nulo não né?");
        }
        return repository.remover(id);
    }
}