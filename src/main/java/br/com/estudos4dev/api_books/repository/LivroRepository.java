package br.com.estudos4dev.api_books.repository;

import br.com.estudos4dev.api_books.entity.model.Autor;
import br.com.estudos4dev.api_books.entity.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTitulo(String nome);
}
