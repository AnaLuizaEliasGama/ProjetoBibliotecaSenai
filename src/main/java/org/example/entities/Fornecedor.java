package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Fornecedor {

    @Id
    private String forId;
    private String forRazaoSocial;
    private String forNomeFantasia;
    private String forCnpj;
    private String forInscricaoEstadual;
    private String forInscricaoMunicipal;
    private String forContatoResponsavel;
    private LocalDate forDataCadastro;
    private String forObservacoes;
    private boolean forSAtivo;

    public Fornecedor() {
        this.forDataCadastro = LocalDate.now();
        this.forSAtivo = true;
    }

    public Fornecedor(String forId, String forRazaoSocial, String forNomeFantasia, String forCnpj,
                      String forInscricaoEstadual, String forInscricaoMunicipal, String forContatoResponsavel,
                      String forObservacoes, boolean forSAtivo) {
        this();
        this.forId = forId;
        this.forRazaoSocial = forRazaoSocial;
        this.forNomeFantasia = forNomeFantasia;
        this.forCnpj = forCnpj;
        this.forInscricaoEstadual = forInscricaoEstadual;
        this.forInscricaoMunicipal = forInscricaoMunicipal;
        this.forContatoResponsavel = forContatoResponsavel;
        this.forObservacoes = forObservacoes;
        this.forSAtivo = forSAtivo;
    }
}