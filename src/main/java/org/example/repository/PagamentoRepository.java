package org.example.repository;

import org.example.entities.Pagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositório para gerenciar formas de pagamento.
 */
public class PagamentoRepository {

    private List<Pagamento> pagamentos;

    public PagamentoRepository() {
        this.pagamentos = new ArrayList<>();
    }

    /**
     * Adiciona uma nova forma de pagamento.
     */
    public void adicionar(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo.");
        }

        // Verifica se já existe um pagamento com esse ID
        if (buscarPorId(pagamento.getId()).isPresent()) {
            throw new IllegalArgumentException("Já existe uma forma de pagamento com o ID: " + pagamento.getId());
        }

        validarPagamento(pagamento);
        pagamentos.add(pagamento);
    }

    /**
     * Atualiza uma forma de pagamento existente pelo ID.
     *
     * @return true se atualizado, false se não encontrado
     */
    public boolean atualizar(Pagamento pagamentoAtualizado) {
        if (pagamentoAtualizado == null) {
            throw new IllegalArgumentException("Pagamento não pode ser nulo.");
        }

        validarPagamento(pagamentoAtualizado);

        for (int i = 0; i < pagamentos.size(); i++) {
            Pagamento pagamento = pagamentos.get(i);
            if (pagamento.getId() == pagamentoAtualizado.getId()) {
                pagamentos.set(i, pagamentoAtualizado);
                return true; // Encontrou e atualizou
            }
        }
        return false; // Não encontrou
    }

    /**
     * Remove uma forma de pagamento pelo ID.
     *
     * @return true se removido, false se não encontrado
     */
    public boolean remover(int id) {
        return pagamentos.removeIf(p -> p.getId() == id);
    }

    /**
     * Busca uma forma de pagamento pelo ID.
     *
     * @return Optional com o pagamento, ou vazio se não encontrado
     */
    public Optional<Pagamento> buscarPorId(int id) {
        return pagamentos.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
    }

    /**
     * Busca formas de pagamento pelo tipo (ex: "crédito", "pix", etc).
     */
    public List<Pagamento> buscarPorTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return pagamentos.stream()
                .filter(p -> p.getTipo().toLowerCase().contains(tipo.toLowerCase()))
                .toList();
    }

    /**
     * Lista todas as formas de pagamento ativas.
     */
    public List<Pagamento> listarAtivos() {
        return pagamentos.stream()
                .filter(Pagamento::isAtivo)
                .toList();
    }

    /**
     * Lista todas as formas de pagamento que permitem troco.
     */
    public List<Pagamento> listarComTroco() {
        return pagamentos.stream()
                .filter(Pagamento::isPermiteTroco)
                .toList();
    }

    /**
     * Retorna todas as formas de pagamento cadastradas.
     */
    public List<Pagamento> listarTodos() {
        return new ArrayList<>(pagamentos);
    }

    /**
     * Valida campos obrigatórios de um pagamento.
     */
    private void validarPagamento(Pagamento pagamento) {
        if (pagamento.getDescricao() == null || pagamento.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição é obrigatória.");
        }
        if (pagamento.getTipo() == null || pagamento.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo é obrigatório.");
        }
        if (pagamento.getNumeroParcelas() < 1) {
            throw new IllegalArgumentException("Número de parcelas deve ser pelo menos 1.");
        }
        if (pagamento.getDiasEntreParcelas() < 0) {
            throw new IllegalArgumentException("Dias entre parcelas não podem ser negativos.");
        }
        if (pagamento.getTaxaPercentual() < 0) {
            throw new IllegalArgumentException("Taxa percentual não pode ser negativa.");
        }
    }
}