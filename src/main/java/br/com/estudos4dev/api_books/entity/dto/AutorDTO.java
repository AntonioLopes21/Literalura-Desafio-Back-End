package br.com.estudos4dev.api_books.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}
