package br.com.estudos4dev.api_books.entity.dto;

import br.com.estudos4dev.api_books.entity.model.Linguagem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LinguagemDTO(
        @JsonProperty("languages") List<Linguagem> linguagens
) {}
