package org.example.test_java_rest_jpa.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.in.CreateBookUseCasePort;
import org.example.test_java_rest_jpa.application.usecases.CreateBookUseCase;
import org.example.test_java_rest_jpa.mocks.BookPersistenceMock;
import org.junit.Test;

public class CreateBookUseCaseTest {

    @Test
    public void create() {
        // arrange
        BookDTO inputBook = new BookDTO("isbn", "title", "author", "description", "reader");
        BookPersistenceMock repo = new BookPersistenceMock();
        repo.createOutput = inputBook;
        CreateBookUseCasePort usecase = new CreateBookUseCase(repo);

        // act
        BookDTO createdBook = usecase.create(inputBook);

        // assert
        assertNotNull(createdBook);
        assertEquals(inputBook, createdBook);
        assertEquals(inputBook, repo.createInput);
    }
}
