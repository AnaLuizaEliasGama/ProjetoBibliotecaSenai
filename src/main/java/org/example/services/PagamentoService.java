package org.example.services;

import org.example.entities.Pagamento;
import org.example.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    /**
     * Lista todas as formas de pagamento.
     */
    public List<Pagamento> listarTodos() {
        return repository.listarTodos();
    }

    /**
     * Busca uma forma de pagamento pelo ID.
     * @return Optional com o pagamento, ou vazio se não encontrado
     */
    public Optional<Pagamento> buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    /**
     * Salva uma nova forma de pagamento.
     * @throws IllegalArgumentException se houver erro de validação
     */
    public void salvar(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo.");
        }
        repository.adicionar(pagamento);
    }

    /**
     * Atualiza uma forma de pagamento existente.
     * @return true se atualizado, false se não encontrado
     */
    public boolean atualizar(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo.");
        }
        return repository.atualizar(pagamento);
    }

    /**
     * Remove uma forma de pagamento pelo ID.
     * @return true se removido, false se não encontrado
     */
    public boolean remover(int id) {
        return repository.remover(id);
    }

    /**
     * Busca formas de pagamento por tipo (ex: "pix", "crédito").
     */
    public List<Pagamento> buscarPorTipo(String tipo) {
        return repository.buscarPorTipo(tipo);
    }

    /**
     * Lista apenas as formas de pagamento ativas.
     */
    public List<Pagamento> listarAtivos() {
        return repository.listarAtivos();
    }
}