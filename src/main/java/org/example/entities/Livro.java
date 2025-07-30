package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity // Declara que esta classe é uma entidade JPA
@Table(name = "livro") // Define o nome da tabela no banco de dados
@Getter // Gerado pelo Lombok para getters
@Setter // Gerado pelo Lombok para setters
public class Livro {

    @Id // Define o campo como chave primária
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "isbn", nullable = false, unique = true, length = 13)
    private String isbn;

    @Column(name = "editora", length = 100)
    private String editora;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @Column(name = "edicao", length = 20)
    private String edicao;

    @Column(name = "idioma", length = 50)
    private String idioma;

    // Construtor padrão (obrigatório para JPA)
    public Livro() {}

    // Construtor com parâmetros
    public Livro(String id, String titulo, String isbn, String editora, String autor, String edicao, String idioma) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.editora = editora;
        this.autor = autor;
        this.edicao = edicao;
        this.idioma = idioma;
    }
}