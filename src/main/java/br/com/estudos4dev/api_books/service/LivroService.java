package br.com.estudos4dev.api_books.service;

import br.com.estudos4dev.api_books.entity.dto.AutorDTO;
import br.com.estudos4dev.api_books.entity.dto.LivroDTO;
import br.com.estudos4dev.api_books.entity.dto.RespostaApiDTO;

import br.com.estudos4dev.api_books.entity.model.Autor;
import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LivroService {
    ConsumoApi consumo = new ConsumoApi();
    private List<Livro> listaDeLivros;


    @Autowired
    private final LivroRepository livroRepository;
    private final ObjectMapper obj = new ObjectMapper();

    public static void exibeMenu() throws IOException, InterruptedException {

        System.out.println("Bem vindo ao Literalura:\n" +
                "1 - buscar livro por título\n" +
                "2 - listar livros registrados\n" +
                "3 - listar autores registrados\n" +
                "4 - listar autores vivos em um determinado ano\n" +
                "5 - listar livros em um determinado idioma\n" +
                "6 - listar livros na api\n" +
                "\n" +
                "0 - sair" );
    }

    public void findBookByTitleAndSave(String titulo) throws IOException, InterruptedException {
        RespostaApiDTO apiResponse = consumo.consumirApi(titulo);

        System.out.println(apiResponse);

    }

    public List<LivroDTO> listaDeLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> new LivroDTO(
                        livro.getId(),
                        livro.getTitulo(),
                        List.of(new AutorDTO(
                                livro.getAutor().getNome(),
                                livro.getAutor().getAnoNascimento(),
                                livro.getAutor().getAnoMorte()
                        )),
                        livro.getLinguagens(),
                        livro.getQuantidadeDownloads()
                ))
                .toList();
    }

    public List<LivroDTO> livrosPorIdioma(String idioma) {
        return livroRepository.findAll()
                .stream()
                .filter(livro -> livro.getLinguagens().contains(idioma))
                .map(livro -> new LivroDTO(
                        livro.getId(),
                        livro.getTitulo(),
                        List.of(new AutorDTO(
                                livro.getAutor().getNome(),
                                livro.getAutor().getAnoNascimento(),
                                livro.getAutor().getAnoMorte()
                        )),
                        livro.getLinguagens(),
                        livro.getQuantidadeDownloads()
                ))
                .toList();
    }

    public void requisicoesApi() throws IOException, InterruptedException {
        String url = "https://gutendex.com/books";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        var apiResponse = obj.readValue(response.body(), RespostaApiDTO.class);
        System.out.println("dados vindos da api: \n" + apiResponse);
    }


}
