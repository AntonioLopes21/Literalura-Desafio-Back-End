package br.com.estudos4dev.api_books.entity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {
    @Id
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ElementCollection
    @CollectionTable(name = "livro_linguagens", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "linguagem")
    private List<String> linguagens;

    private int quantidadeDownloads;
}
