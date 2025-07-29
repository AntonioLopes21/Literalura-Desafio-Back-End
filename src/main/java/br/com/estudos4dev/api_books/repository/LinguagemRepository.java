package br.com.estudos4dev.api_books.repository;

import br.com.estudos4dev.api_books.entity.model.Linguagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinguagemRepository extends JpaRepository<Linguagem, Long> {
}
