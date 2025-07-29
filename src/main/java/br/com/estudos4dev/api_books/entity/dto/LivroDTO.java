package br.com.estudos4dev.api_books.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonProperty("id")
                       Long id,

                       @JsonProperty("title")
                       String nome,

                       @JsonProperty("authors")
                       List<AutorDTO> autores,

                       @JsonProperty("languages")
                       List<String> linguagens,

                       @JsonProperty("download_count")
                       Integer quantidadeDownloads

) { }
