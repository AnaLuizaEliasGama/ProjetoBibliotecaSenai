package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa um cliente no sistema.
 */
@Getter
@Setter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proid;

    private String cliId; // Identificador único do cliente
    private String cliNome; // Nome completo do cliente
    private String cliCpf; // CPF do cliente
    private String cliRg; // RG do cliente
    private LocalDate cliDataNascimento; // Data de nascimento do cliente
    private String cliSexo; // Sexo do cliente (ex.: "M", "F", "Outro")
    private LocalDate cliDataCadastro; // Data de cadastro do cliente no sistema
    private String cliObservacoes; // Observações adicionais sobre o cliente
    private boolean cliAtivo; // Indica se o cliente está ativo no sistema

    public Cliente() {
    }

    public Cliente(Long proid, String cliId, String cliNome, String cliCpf, String cliRg, LocalDate cliDataNascimento, String cliSexo, LocalDate cliDataCadastro, String cliObservacoes, boolean cliAtivo) {
        this.proid = proid;
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cliCpf = cliCpf;
        this.cliRg = cliRg;
        this.cliDataNascimento = cliDataNascimento;
        this.cliSexo = cliSexo;
        this.cliDataCadastro = cliDataCadastro;
        this.cliObservacoes = cliObservacoes;
        this.cliAtivo = cliAtivo;
    }


}