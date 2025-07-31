package br.com.estudos4dev.api_books.repository;

import br.com.estudos4dev.api_books.entity.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoMorte >= :ano OR a.anoMorte = 0)")
    List<Autor> findAutoresVivosEmAno(@Param("ano") int ano);
}
