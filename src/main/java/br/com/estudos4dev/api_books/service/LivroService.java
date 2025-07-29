package br.com.estudos4dev.api_books.service;

import br.com.estudos4dev.api_books.entity.model.Livro;
import br.com.estudos4dev.api_books.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public Optional<Livro> encontrarPorTituloIgnoreCase(String titulo) {
        return livroRepository.searchByTituloIgnoreCase(titulo);
    }
}
