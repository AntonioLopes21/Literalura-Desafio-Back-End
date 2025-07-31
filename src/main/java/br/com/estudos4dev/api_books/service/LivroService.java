package br.com.estudos4dev.api_books.service;

import br.com.estudos4dev.api_books.entity.dto.AutorDTO;
import br.com.estudos4dev.api_books.entity.dto.LivroDTO;
import br.com.estudos4dev.api_books.entity.dto.RespostaApiDTO;

import br.com.estudos4dev.api_books.entity.model.Autor;
import br.com.estudos4dev.api_books.entity.model.Linguagem;
import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.repository.AutorRepository;
import br.com.estudos4dev.api_books.repository.LinguagemRepository;
import br.com.estudos4dev.api_books.repository.LivroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LivroService {
    ConsumoApi consumo = new ConsumoApi();

    @Autowired
    private final LinguagemRepository linguagemRepository;

    @Autowired
    private final AutorRepository autorRepository;
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
                "\n" +
                "0 - sair" );
    }

    public String findBookByTitleAndSave(String titulo) throws IOException, InterruptedException {
        String tituloFormatado = titulo.replaceAll(" ", "+");
        RespostaApiDTO apiResponse = consumo.consumirApi(tituloFormatado);
        LivroDTO dto = apiResponse.converteDadosParaLivroDTO();
        assert dto != null;

        Livro conversao = dto.toEntity();

        if(livroRepository.existsByTitulo(conversao.getTitulo())) {

            return "Livro já existe na base de dados." + conversao.getTitulo();

        } else {

//        System.out.println("---\n" + apiResponse.converteDadosParaLivroDTO() + "\n---");
            livroRepository.save(conversao);
            return "Livro salvo com sucesso!\n\nInformações:\n" + dto;
        }


    }

    public void listarAutoresRegistrados() {
        List<Autor> listaDeAutores = autorRepository.findAll();


        System.out.println("--LISTA DE AUTORES CADASTRADOS--\n");

        for(Autor autores : listaDeAutores) {
            System.out.println("Autor:" + autores.getId());
            System.out.println("Nome do autor: " + autores.getNome() + "\n-------");
        }
    }


    public void listarLivros() throws IOException, InterruptedException {
        List<Livro> listaLivros = livroRepository.findAll();

        for(Livro l : listaLivros) {
            System.out.println("-----LIVRO-----");
            System.out.println("id do Livro: " +l.getId());
            System.out.println("Nome do Livro: " +l.getTitulo());
            String nomeAutor = l.getAutor() != null ? l.getAutor().getNome() : "Autor desconhecido";

            System.out.println("Nome do autor: " + nomeAutor);
            System.out.println("Linguagens que o livro está traduzido: " +l.getLinguagens());
            System.out.println("Quantidade de downloads: " +l.getQuantidadeDownloads());
            System.out.println("---------------");
        }


    }

    public void listarAutoresVivosEmDeterminadaEpoca(int ano) {
        Optional<List<Autor>> autores = Optional.ofNullable(autorRepository.findAutoresVivosEmAno(ano));

            autores.ifPresent(autoresListaOptional -> {
                System.out.println("Lista de autores vivos no ano de :" + ano);
                for(Autor autor : autoresListaOptional) {
                    System.out.println("Autor: " + autor.getNome() + "\ndata de nascimento:" + autor.getAnoNascimento() + "\ndata de falecimento:" + autor.getAnoMorte());
                    System.out.println("-----");
                }
            });
        }

    public void listaLivrosPorIdioma(String idioma) {
        livroRepository.findAll()
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
                .forEach(livroDTO -> {
                    System.out.println("Título: " + livroDTO.nome());
                    System.out.println("Autor: " + livroDTO.autor().get(0).nome());
                    System.out.println("Linguagens disponíveis: " + livroDTO.linguagens());
                    System.out.println("-----------------------");
                });
    }

}


