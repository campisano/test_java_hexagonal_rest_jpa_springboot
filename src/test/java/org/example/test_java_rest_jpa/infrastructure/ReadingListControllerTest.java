package org.example.test_java_rest_jpa.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.test_java_rest_jpa.application.BookDto;
import org.example.test_java_rest_jpa.application.BookService;
import org.example.test_java_rest_jpa.domain.Book;
import org.example.test_java_rest_jpa.infrastructure.ReadingListController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReadingListControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private ReadingListController controller;

    @Test
    public void addToReadingList() {
        // arrange
        prepareBookServiceCreate("my_isbn", "my_title", "my_author", "my_description");

        // act
        BookDto request = new BookDto("my_isbn", "my_title", "my_author", "my_description");
        ResponseEntity<BookDto> response = controller.addToReadingList("my_user", request);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("my_isbn", response.getBody().getIsbn());
        assertEquals("my_title", response.getBody().getTitle());
        assertEquals("my_author", response.getBody().getAuthor());
        assertEquals("my_description", response.getBody().getDescription());
    }

    private void prepareBookServiceCreate(String isbn, String title, String author, String description) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        when(bookService.create(
                 ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(book);
    }
}
