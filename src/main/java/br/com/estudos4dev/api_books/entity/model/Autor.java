package br.com.estudos4dev.api_books.entity.model;

import br.com.estudos4dev.api_books.entity.dto.AutorDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int anoNascimento;
    private int anoMorte;

    @OneToMany(mappedBy = "autor")
    private List<Livro> listaDeLivros;

}
