package br.com.estudos4dev.api_books.entity.dto;

import br.com.estudos4dev.api_books.entity.model.Autor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(
        @JsonProperty("name") String nome,
        @JsonProperty("birth_year") Integer anoNascimento,
        @JsonProperty("death_year") Integer anoFalecimento
) {
    @Override
    public String toString() {
        return "Autor:" +
                "nome='" + nome + '\'' +
                ", anoNascimento=" + anoNascimento +
                ", anoFalecimento=" + anoFalecimento +
                "\n";
    }

    public Autor toEntity() {
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setAnoNascimento(anoNascimento);
        autor.setAnoMorte(anoFalecimento);

        return autor;
    }
}
