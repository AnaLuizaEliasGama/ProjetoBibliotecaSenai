package org.example.entities;

import jakarta.persistence.*;

@Entity  // ✅ Essencial: marca a classe como entidade JPA
@Table(name = "pagamento")  // ✅ Nome da tabela no banco (opcional, mas recomendado)
public class Pagamento {

    @Id  // ✅ Define como chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ Gera ID automaticamente (1, 2, 3...)
    private int id;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "numero_parcelas")
    private int numeroParcelas;

    @Column(name = "dias_entre_parcelas")
    private int diasEntreParcelas;

    @Column(name = "permite_troco")
    private boolean permiteTroco;

    @Column(name = "taxa_percentual")
    private double taxaPercentual;

    @Column(name = "ativo")
    private boolean ativo;

    // Construtor padrão (obrigatório para JPA)
    public Pagamento() {
        this.ativo = true; // Por padrão, forma de pagamento está ativa
    }

    // Construtor completo
    public Pagamento(int id, String descricao, String tipo, int numeroParcelas,
                     int diasEntreParcelas, boolean permiteTroco, double taxaPercentual, boolean ativo) {
        this(); // Chama o construtor padrão
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.numeroParcelas = numeroParcelas;
        this.diasEntreParcelas = diasEntreParcelas;
        this.permiteTroco = permiteTroco;
        this.taxaPercentual = taxaPercentual;
        this.ativo = ativo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public int getDiasEntreParcelas() {
        return diasEntreParcelas;
    }

    public void setDiasEntreParcelas(int diasEntreParcelas) {
        this.diasEntreParcelas = diasEntreParcelas;
    }

    public boolean isPermiteTroco() {
        return permiteTroco;
    }

    public void setPermiteTroco(boolean permiteTroco) {
        this.permiteTroco = permiteTroco;
    }

    public double getTaxaPercentual() {
        return taxaPercentual;
    }

    public void setTaxaPercentual(double taxaPercentual) {
        this.taxaPercentual = taxaPercentual;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}