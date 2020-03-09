package org.example.test_java_rest_jpa.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.in.FindReaderBooksUseCasePort;
import org.example.test_java_rest_jpa.application.usecases.FindReaderBooksUseCase;
import org.example.test_java_rest_jpa.mocks.BookPersistenceMock;
import org.junit.Test;

public class FindReaderBooksUseCaseTest {

    @Test
    public void create() {
        // arrange
        String inputReader = "reader";
        List<BookDTO> readerBooks = Arrays.asList(new BookDTO("isbn", "title", "author", "description", inputReader));
        BookPersistenceMock repo = new BookPersistenceMock();
        repo.findByReaderOutput = readerBooks;
        FindReaderBooksUseCasePort useCase = new FindReaderBooksUseCase(repo);

        // act
        List<BookDTO> findBooks = useCase.findByReader(inputReader);

        // assert
        assertNotNull(findBooks);
        assertEquals(1, findBooks.size());
        assertEquals(readerBooks, findBooks);
    }
}
