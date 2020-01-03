package org.example.test_java_rest_jpa.infrastructure;

import java.util.ArrayList;
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
            new BookServiceMock(new ArrayList<Book>()));

        // act
        BookDto request_body = new BookDto(
            "isbn", "title", "author", "description");
        ResponseEntity<BookDto> response =
            controller.addToReadingList("user", request_body);

        // assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("isbn", response.getBody().getIsbn());
        assertEquals("title", response.getBody().getTitle());
        assertEquals("author", response.getBody().getAuthor());
        assertEquals("description", response.getBody().getDescription());
    }

    @Test
    public void readersBook() {
        // arrange
        Book book = new Book();
        book.setReader("user");
        book.setIsbn("isbn");
        book.setTitle("title");
        book.setAuthor("author");
        book.setDescription("description");

        List<Book> existentBooks = new ArrayList<Book>();
        existentBooks.add(book);

        ReadingListController controller = new ReadingListController(
            new BookServiceMock(existentBooks));

        // act
        ResponseEntity<List<BookDto>> response =
            controller.readersBooks("user");

        // assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("isbn", response.getBody().get(0).getIsbn());
        assertEquals("title", response.getBody().get(0).getTitle());
        assertEquals("author", response.getBody().get(0).getAuthor());
        assertEquals("description", response.getBody().get(0).getDescription());
    }
}

class BookServiceMock extends BookService {

    private List<Book> books;

    public BookServiceMock(List<Book> books) {
        super(null);
        this.books = books;
    }

    public Book create(
        String isbn, String title, String author, String description)
    {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);
        return book;
    }

    public List<Book> findByReader(String reader) {
        return books;
    }

    public Book save(Book book) {
        books.add(book);
        return book;
    }
}
