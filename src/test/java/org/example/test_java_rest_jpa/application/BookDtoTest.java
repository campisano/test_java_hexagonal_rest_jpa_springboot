package org.example.test_java_rest_jpa.application;

import org.junit.Test;
import org.example.test_java_rest_jpa.application.BookDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookDtoTest {

    @Test
    public void translate() {
        // act
        BookDto bookDto = new BookDto(
            "isbn", "title", "author", "description");

        // assert
        assertEquals("isbn", bookDto.getIsbn());
        assertEquals("title", bookDto.getTitle());
        assertEquals("author", bookDto.getAuthor());
        assertEquals("description", bookDto.getDescription());
    }
}
