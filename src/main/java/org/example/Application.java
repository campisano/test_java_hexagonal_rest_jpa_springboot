package org.example;

import org.example.application.ports.out.AuthorsRepositoryPort;
import org.example.application.ports.out.BooksRepositoryPort;
import org.example.application.usecases.AddAuthorUseCase;
import org.example.application.usecases.AddBookUseCase;
import org.example.application.usecases.GetBookUseCase;
import org.example.application.usecases.ListAllBooksUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Configuration
class InjectionProvider {

    private AddBookUseCase addBookUseCase;
    private GetBookUseCase getBookUseCase;
    private ListAllBooksUseCase listAllBooksUseCase;
    private AddAuthorUseCase addAuthorUseCase;

    @Autowired
    public InjectionProvider(AuthorsRepositoryPort authorsRepository, BooksRepositoryPort booksRepository) {
        addBookUseCase = new AddBookUseCase(booksRepository, authorsRepository);
        getBookUseCase = new GetBookUseCase(booksRepository);
        listAllBooksUseCase = new ListAllBooksUseCase(booksRepository);
        addAuthorUseCase = new AddAuthorUseCase(authorsRepository);
    }

    @Bean
    public AddBookUseCase getAddBookUseCase() {
        return addBookUseCase;
    }

    @Bean
    public GetBookUseCase getGetBookUseCase() {
        return getBookUseCase;
    }

    @Bean
    public ListAllBooksUseCase getListAllBooksUseCase() {
        return listAllBooksUseCase;
    }

    @Bean
    public AddAuthorUseCase getAddAuthorUseCase() {
        return addAuthorUseCase;
    }
}
