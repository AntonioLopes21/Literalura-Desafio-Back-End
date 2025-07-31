package br.com.estudos4dev.api_books.entity.model;

import br.com.estudos4dev.api_books.entity.dto.AutorDTO;
import br.com.estudos4dev.api_books.entity.dto.LivroDTO;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @CollectionTable(name = "livro_linguagens", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "linguagem")

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> linguagens;

    private int quantidadeDownloads;


    public LivroDTO toDTO() {
        AutorDTO autorDTO = new AutorDTO(
                autor.getNome(),
                autor.getAnoNascimento(),
                autor.getAnoMorte()
        );

        return new LivroDTO(
                id,
                titulo,
                List.of(autorDTO),
                linguagens,
                quantidadeDownloads
        );
    }


}
