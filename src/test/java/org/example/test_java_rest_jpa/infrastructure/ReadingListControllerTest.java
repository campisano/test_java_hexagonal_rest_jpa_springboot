package org.example.test_java_rest_jpa.infrastructure;

import java.util.List;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.example.test_java_rest_jpa.application.BookDto;
import org.example.test_java_rest_jpa.application.BookService;
import org.example.test_java_rest_jpa.domain.Book;
import org.example.test_java_rest_jpa.infrastructure.ReadingListController;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingListControllerTest {

    @Test
    public void addToReadingList() {
        // arrange
        ReadingListController controller = new ReadingListController(
            new BookServiceMock());

        // act
        BookDto request_body = new BookDto("my_isbn", "my_title", "my_author", "my_description");
        ResponseEntity<BookDto> response = controller.addToReadingList("my_user", request_body);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("my_isbn", response.getBody().getIsbn());
        assertEquals("my_title", response.getBody().getTitle());
        assertEquals("my_author", response.getBody().getAuthor());
        assertEquals("my_description", response.getBody().getDescription());
    }

    class BookServiceMock implements BookService {

        public Book create(String isbn, String title, String author, String description)
        {
            Book book = new Book();
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setAuthor(author);
            book.setDescription(description);
            return book;
        }

        public List<Book> findByReader(String reader) {
            return null;
        }

        public Book save(Book book) {
            return null;
        }
    }
}
