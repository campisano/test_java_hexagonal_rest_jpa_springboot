package org.example.test_java_rest_jpa.application;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.example.test_java_rest_jpa.application.BookDtoFactory;
import org.example.test_java_rest_jpa.domain.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookDtoFactoryTest {

    @Test
    public void translate() {
        // arrange
        Book book = new Book();
        book.setReader("user");
        book.setIsbn("isbn");
        book.setTitle("title");
        book.setAuthor("author");
        book.setDescription("description");

        // act
        BookDto bookDto = BookDtoFactory.translate(book);

        // assert
        assertNotNull(bookDto);
        assertEquals("isbn", bookDto.getIsbn());
        assertEquals("title", bookDto.getTitle());
        assertEquals("author", bookDto.getAuthor());
        assertEquals("description", bookDto.getDescription());
    }

    @Test
    public void translateList() {
        // arrange
        List<Book> books = new ArrayList<Book>();
        Book book = new Book();
        book.setReader("user");
        book.setIsbn("isbn");
        book.setTitle("title");
        book.setAuthor("author");
        book.setDescription("description");
        books.add(book);

        // act
        List<BookDto> bookDtos = BookDtoFactory.translate(books);

        // assert
        assertNotNull(bookDtos);
        assertEquals(1, bookDtos.size());
        assertEquals("isbn", bookDtos.get(0).getIsbn());
        assertEquals("title", bookDtos.get(0).getTitle());
        assertEquals("author", bookDtos.get(0).getAuthor());
        assertEquals("description", bookDtos.get(0).getDescription());
    }
}
