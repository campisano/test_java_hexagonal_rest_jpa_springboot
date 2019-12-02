package org.example.test_java_rest_jpa.application;

import java.util.List;
import java.util.ArrayList;
import org.example.test_java_rest_jpa.domain.Book;

public class BookDtoFactory {

    public static BookDto translate(Book book) {

        return new BookDto(
            book.getIsbn(),
            book.getTitle(),
            book.getAuthor(),
            book.getDescription());
    }

    public static List<BookDto> translate(List<Book> books) {

        ArrayList<BookDto> booksDto = new ArrayList<BookDto>();

        for(Book book : books) {
            booksDto.add(translate(book));
        }

        return booksDto;
    }
}
