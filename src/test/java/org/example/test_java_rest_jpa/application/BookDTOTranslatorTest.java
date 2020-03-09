package org.example.test_java_rest_jpa.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.dtos.BookDTOTranslator;
import org.example.test_java_rest_jpa.domain.Book;
import org.junit.Test;

public class BookDTOTranslatorTest {

    @Test
    public void toDTO() {
        // arrange
        Book book = new Book("isbn", "title", "author", "description", "reader");

        // act
        BookDTO bookDto = BookDTOTranslator.toDTO(book);

        // assert
        assertNotNull(bookDto);
        assertEquals("isbn", bookDto.getIsbn());
        assertEquals("title", bookDto.getTitle());
        assertEquals("author", bookDto.getAuthor());
        assertEquals("description", bookDto.getDescription());
        assertEquals("reader", bookDto.getReader());
    }

    @Test
    public void fromDTO() {
        // arrange
        BookDTO bookDto = new BookDTO("isbn", "title", "author", "description", "reader");

        // act
        Book book = BookDTOTranslator.fromDTO(bookDto);

        // assert
        assertNotNull(book);
        assertEquals("isbn", book.getIsbn());
        assertEquals("title", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals("description", book.getDescription());
        assertEquals("reader", book.getReader());
    }
}
