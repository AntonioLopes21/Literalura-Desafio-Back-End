package br.com.estudos4dev.api_books.service;

import br.com.estudos4dev.api_books.entity.dto.LivroDTO;
import br.com.estudos4dev.api_books.entity.dto.RespostaApiDTO;
import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class ConsumoApi {
    private ObjectMapper obj = new ObjectMapper();

    public RespostaApiDTO consumirApi(String endereco) throws IOException, InterruptedException {
        String url = "https://gutendex.com/books/?search=";
        String enderecoCompleto = url + endereco;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(enderecoCompleto))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("dados vindos da api: \n" +response.body());

        RespostaApiDTO apiResponse = obj.readValue(response.body(), RespostaApiDTO.class);

        return apiResponse;
    }
}
