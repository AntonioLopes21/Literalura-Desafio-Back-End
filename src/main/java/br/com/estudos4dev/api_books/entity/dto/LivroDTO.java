package br.com.estudos4dev.api_books.entity.dto;

import br.com.estudos4dev.api_books.entity.model.Autor;
import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.repository.AutorRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonProperty("id")
                       Long id,

                       @JsonProperty("title")
                       String nome,

                       @JsonProperty("authors")
                       List<AutorDTO> autor,

                       @JsonProperty("languages")
                       List<String> linguagens,

                       @JsonProperty("download_count")
                       Integer quantidadeDownloads

) {
    @Override
    public String toString() {
        return "Livro: " +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", autor=" + autor +
                ", linguagens=" + linguagens +
                ", quantidadeDownloads=" + quantidadeDownloads +
                "\n";
    }

    public List<String> languages() {
        return linguagens;
    }

    public Livro toEntity() {
        Livro livro = new Livro();
        livro.setTitulo(nome);

        if (autor != null && !autor.isEmpty()) {
            AutorDTO primeiroAutorDTO = autor.get(0);
            Autor autorEntity = new Autor();
            autorEntity.setNome(primeiroAutorDTO.nome());
            autorEntity.setAnoNascimento(primeiroAutorDTO.anoNascimento());
            autorEntity.setAnoMorte(primeiroAutorDTO.anoFalecimento());
            livro.setAutor(autorEntity);}

        livro.setLinguagens(linguagens);
        livro.setQuantidadeDownloads(quantidadeDownloads);
        return livro;
    }

}
