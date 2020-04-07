package org.example;

import org.example.application.ports.out.AuthorsRepositoryPort;
import org.example.application.ports.out.BooksRepositoryPort;
import org.example.application.usecases.AddAuthorUseCase;
import org.example.application.usecases.AddBookUseCase;
import org.example.application.usecases.GetBookUseCase;
import org.example.application.usecases.ListAllBooksUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Service
class SpringAddBookUseCase extends AddBookUseCase {
    public SpringAddBookUseCase(BooksRepositoryPort booksRepository, AuthorsRepositoryPort authorsRepository) {
        super(booksRepository, authorsRepository);
    }

}

@Service
class SpringGetBookUseCase extends GetBookUseCase {
    public SpringGetBookUseCase(BooksRepositoryPort booksRepository) {
        super(booksRepository);
    }
}

@Service
class SpringListAllBooksUseCase extends ListAllBooksUseCase {
    public SpringListAllBooksUseCase(BooksRepositoryPort booksRepository) {
        super(booksRepository);
    }
}

@Service
class SpringAddAuthorUseCase extends AddAuthorUseCase {
    public SpringAddAuthorUseCase(AuthorsRepositoryPort authorsRepository) {
        super(authorsRepository);
    }
}
