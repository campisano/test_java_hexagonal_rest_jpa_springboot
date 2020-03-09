package org.example.test_java_rest_jpa.application.ports.dtos;

import org.example.test_java_rest_jpa.domain.Book;

public class BookDTOTranslator {

    public static Book fromDTO(BookDTO bookDTO) {
        return new Book(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getDescription(),
                bookDTO.getReader());
    }

    public static BookDTO toDTO(final Book book) {
        return new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getDescription(), book.getReader());
    }
}
