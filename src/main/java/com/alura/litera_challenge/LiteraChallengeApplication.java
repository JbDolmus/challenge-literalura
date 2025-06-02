package com.alura.litera_challenge;

import com.alura.litera_challenge.principal.Main;
import com.alura.litera_challenge.repository.AuthorRepository;
import com.alura.litera_challenge.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class LiteraChallengeApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(bookRepository, authorRepository);
		main.showMenu();
	}
}
