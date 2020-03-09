package org.example.test_java_rest_jpa;

import org.example.test_java_rest_jpa.application.ports.out.BookPersistencePort;
import org.example.test_java_rest_jpa.application.usecases.CreateBookUseCase;
import org.example.test_java_rest_jpa.application.usecases.FindReaderBooksUseCase;
import org.springframework.beans.factory.annotation.Autowired;
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
class CreateBookUseCaseSpring extends CreateBookUseCase {

    @Autowired
    public CreateBookUseCaseSpring(BookPersistencePort bookPersistence) {
        super(bookPersistence);
    }
}

@Service
class FindReaderBooksUseCaseSpring extends FindReaderBooksUseCase {

    @Autowired
    public FindReaderBooksUseCaseSpring(BookPersistencePort bookPersistence) {
        super(bookPersistence);
    }
}
