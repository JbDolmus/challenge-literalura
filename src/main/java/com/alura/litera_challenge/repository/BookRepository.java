package com.alura.litera_challenge.repository;

import com.alura.litera_challenge.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitulo(String titulo);
    List<Book> searchBooksByIdioma(String language);

}
