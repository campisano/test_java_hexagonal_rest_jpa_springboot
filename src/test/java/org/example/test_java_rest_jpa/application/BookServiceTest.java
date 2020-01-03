package org.example.test_java_rest_jpa.application;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.example.test_java_rest_jpa.application.BookService;
import org.example.test_java_rest_jpa.domain.Book;
import org.example.test_java_rest_jpa.domain.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookServiceTest {

    @Test
    public void create() {
        // arrange
        BookRepository repo = new BookRepositoryMock(null, null);
        BookService service = new BookService(repo);

        // act
        Book book = service.create(
            "isbn", "title", "author", "description");

        // assert
        assertNotNull(book);
        assertEquals("isbn", book.getIsbn());
        assertEquals("title", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals("description", book.getDescription());
    }

    @Test
    public void findByReader() {
        // arrange
        List<Book> expectedBooks = new ArrayList<Book>();
        BookRepository repo = new BookRepositoryMock(expectedBooks, null);
        BookService service = new BookService(repo);

        // act
        List<Book> actualBooks = service.findByReader("reader");

        // assert
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void save() {
        // arrange
        Book expectedBook = new Book();
        BookRepository repo = new BookRepositoryMock(null, expectedBook);
        BookService service = new BookService(repo);

        // act
        Book actualBook = service.save(null);

        // assert
        assertEquals(expectedBook, actualBook);
    }
}

class BookRepositoryMock implements BookRepository {

    private List<Book> expectedBooks;
    private Book expectedBook;

    public BookRepositoryMock(List<Book> expectedBooks, Book expectedBook) {
        this.expectedBooks = expectedBooks;
        this.expectedBook = expectedBook;
    }
    public List<Book> findByReader(String reader) {
        return expectedBooks;
    }

    public Book save(Book book) {
        return expectedBook;
    }
}
