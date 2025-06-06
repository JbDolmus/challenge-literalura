package com.alura.litera_challenge.repository;

import com.alura.litera_challenge.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.titulo ILIKE %:titulo%")
    Optional<Book> findByTitulo(String titulo);

    List<Book> searchBooksByIdioma(String language);

}
