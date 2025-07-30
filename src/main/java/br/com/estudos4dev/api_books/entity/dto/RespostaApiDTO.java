package br.com.estudos4dev.api_books.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaApiDTO (List<LivroDTO> results){
}
