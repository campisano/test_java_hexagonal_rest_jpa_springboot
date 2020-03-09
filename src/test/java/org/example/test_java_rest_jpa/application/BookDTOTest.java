package org.example.test_java_rest_jpa.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.junit.Test;

public class BookDTOTest {

    @Test
    public void creation() {
        // act
        BookDTO book = new BookDTO("isbn", "title", "author", "description", "reader");

        // assert
        assertEquals("isbn", book.getIsbn());
        assertEquals("title", book.getTitle());
        assertEquals("author", book.getAuthor());
        assertEquals("description", book.getDescription());
        assertEquals("reader", book.getReader());
    }
}
