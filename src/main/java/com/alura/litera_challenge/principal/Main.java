package com.alura.litera_challenge.principal;

import com.alura.litera_challenge.model.Author;
import com.alura.litera_challenge.model.Book;
import com.alura.litera_challenge.model.DataBook;
import com.alura.litera_challenge.model.DataResponse;
import com.alura.litera_challenge.repository.AuthorRepository;
import com.alura.litera_challenge.repository.BookRepository;
import com.alura.litera_challenge.service.DataConverter;
import com.alura.litera_challenge.service.QueryAPI;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Scanner teclado = new Scanner(System.in);
    private QueryAPI queryAPI = new QueryAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private DataConverter converter = new DataConverter();
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private List<Book> books;
    private List<Author> authors;

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                
                ************** Menu Principal - Litera_Challenge App **************
                
                1 - Buscar libros por título
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado año
                5 - Listar libros por idioma
                0 - Salir
                """;
            System.out.println(menu);
            System.out.print("Digite la opción aquí: ");

            try {
                opcion = teclado.nextInt();
                teclado.nextLine(); // limpiar el buffer

                switch (opcion) {
                    case 1:
                        searchBookByTitle();
                        break;
                    case 2:
                        getRegisteredBooks();
                        break;
                    case 3:
                        getRegisteredAuthors();
                        break;
                    case 4:
                        getLivingAuthorsByYear();
                        break;
                    case 5:
                        getBooksByLanguage();
                        break;
                    case 0:
                        System.out.println("Muchas gracias por usar nuestra aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Formato inválido. Por favor, ingrese un número entero.");
                teclado.nextLine();
            }
        }

    }

    private void searchBookByTitle() {
        System.out.print("Ingrese el título del libro que desea buscar: ");
        String title = teclado.nextLine();

        var json = queryAPI.getData(URL_BASE + "?search=" + title.replace(" ", "%20"));
        DataResponse datos = converter.getData(json, DataResponse.class);

        Optional<Book> existingBook = bookRepository.findByTitulo(title);

        System.out.println();

        if (existingBook.isEmpty()) {
            if (!datos.results().isEmpty()) {
                Book book = new Book(datos.results().get(0));
                Author author = datos.results().get(0).authors().get(0);
                bookRepository.save(book);
                authorRepository.save(author);

                System.out.println(book);
            } else {
                System.out.println("Libro no encontrado!!");
            }
        } else {
            System.out.println("Ya existe un libro con ese título.");
        }

    }

    private void getRegisteredBooks() {
        books = bookRepository.findAll();
        System.out.println();
        if(!books.isEmpty()){
            books.forEach(System.out::println);
        }else{
            System.out.println("No hay libros registrados en la base de datos!!\n");
        }

    }

    private void getRegisteredAuthors() {
        authors = authorRepository.findAll();
        System.out.println();
        if(!authors.isEmpty()){
            authors.forEach(System.out::println);
        }else {
            System.out.println("No hay autores registrados en la base de datos!!\n");
        }
    }

    private void getLivingAuthorsByYear() {
        System.out.print("Ingrese el año para visualizar los autores: ");
        int year = teclado.nextInt();
        List<Author> authors1 = authorRepository.searchLivingAuthorsByYear(year);

        System.out.println();

        if(!authors1.isEmpty()){
            authors1.forEach(System.out::println);
        }else{
            System.out.println("No hay autores vivos registrados para el año " + year);
        }
    }

    private void getBooksByLanguage() {
        boolean isValidLanguage;
        String language;
        do {
            System.out.println("Ingrese uno de los siguientes idiomas");
            var menu = """
                    es - Español
                    en - Inglés
                    fr - Francés
                    pt - Portugués
                    """;
            System.out.println(menu);
            System.out.print("Opción elegida: ");
            language = teclado.nextLine();

            if (!language.equalsIgnoreCase("es") && !language.equalsIgnoreCase("en") && !language.equalsIgnoreCase("fr") && !language.equalsIgnoreCase("pt")) {
                System.out.println("Opción inválida!!");
                isValidLanguage = false;
            } else {
                isValidLanguage = true;
            }
        } while (!isValidLanguage);

        List<Book> books = bookRepository.searchBooksByIdioma(language);
        System.out.println();
        if (!books.isEmpty()){
            books.forEach(System.out::println);
        }else {
            System.out.println("No hay Libros registrados con el idioma <"+language+">!!\n");
        }

    }
}
