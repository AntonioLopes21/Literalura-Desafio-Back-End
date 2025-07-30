package br.com.estudos4dev.api_books.service;

import br.com.estudos4dev.api_books.entity.dto.AutorDTO;
import br.com.estudos4dev.api_books.entity.dto.LivroDTO;
import br.com.estudos4dev.api_books.entity.model.Autor;
import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LivroService {

    @Autowired
    private final LivroRepository livroRepository;

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


//    public Optional<Livro> encontrarPorTituloIgnoreCase(String titulo) {
//        Optional<Livro> livro = livroRepository.searchByTituloIgnoreCase(titulo);
//
//        if(livro.isPresent()) {
//            Livro novoLivro = livroRepository.save(livro.get());
//            System.out.println(novoLivro);
//            return livroRepository.searchByTituloIgnoreCase(titulo);
//        }
//
//        return Optional.empty();
//    }

    public void findBookByTitle(String titulo) {
        Optional<Livro> livro = livroRepository.findByTitulo(titulo);

        if (livro.isPresent()) {
            Livro livroEncontrado = livro.get();
            System.out.println("Livro encontrado: " + livroEncontrado.getTitulo());
        } else {
            System.out.println("Livro não encontrado.");
        }
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

//    public List<AutorDTO> listaDeAutores() {
//        if(livroRepository.count() > 0) {
//            List<AutorDTO> autores = livroRepository.findAllAutores()
//                    .stream()
//                    .map(autor -> new AutorDTO(autor.getNome(), autor.getAnoNascimento(), autor.getAnoMorte()))
//                    .toList();
//            autores.forEach(System.out::println);
//            return autores;
//        } else {
//            System.out.println("Nenhum autor cadastrado.");
//            return List.of();
//        }
//    }
//
//    public List<AutorDTO> autoresVivosEmAno(int ano) {
//        return livroRepository.findAllAutores()
//                .stream()
//                .filter(autor -> autor.getAnoMorte() > ano || autor.getAnoMorte() == 0)
//                .map(autor -> new AutorDTO(autor.getNome(), autor.getAnoNascimento(), autor.getAnoMorte()))
//                .toList();
//    }

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


}
