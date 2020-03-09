package org.example.test_java_rest_jpa.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.example.test_java_rest_jpa.adapters.controllers.ReadingListController;
import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.mocks.CreateBookUseCaseMock;
import org.example.test_java_rest_jpa.mocks.FindReaderBooksUseCaseMock;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ReadingListControllerTest {

    @Test
    public void addToReadingList() {
        // arrange
        String requesterUser = "user";
        BookDTO requestBody = new BookDTO("isbn", "title", "author", "description", null);
        CreateBookUseCaseMock useCase = new CreateBookUseCaseMock();
        useCase.createOutput = new BookDTO("isbn", "title", "author", "description", requesterUser);
        ReadingListController controller = new ReadingListController(useCase, null);

        // act
        ResponseEntity<BookDTO> response = controller.addToReadingList(requesterUser, requestBody);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(requesterUser, response.getBody().getReader());
        assertEquals("isbn", response.getBody().getIsbn());
        assertEquals("title", response.getBody().getTitle());
        assertEquals("author", response.getBody().getAuthor());
        assertEquals("description", response.getBody().getDescription());
        assertEquals("description", response.getBody().getDescription());
    }

    @Test
    public void readersBook() {
        // arrange
        String requesterUser = "user";
        BookDTO readerBooks = new BookDTO("isbn", "title", "author", "description", requesterUser);
        FindReaderBooksUseCaseMock useCase = new FindReaderBooksUseCaseMock();
        useCase.findByReaderOutput = Arrays.asList(readerBooks);
        ReadingListController controller = new ReadingListController(null, useCase);

        // act
        ResponseEntity<List<BookDTO>> response = controller.readersBooks(requesterUser);

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("isbn", response.getBody().get(0).getIsbn());
        assertEquals("title", response.getBody().get(0).getTitle());
        assertEquals("author", response.getBody().get(0).getAuthor());
        assertEquals("description", response.getBody().get(0).getDescription());
    }
}
