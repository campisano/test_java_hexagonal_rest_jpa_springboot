package org.example.test_java_rest_jpa.application;

import java.util.List;
import org.example.test_java_rest_jpa.domain.Book;

public interface BookService {

    Book create(String isbn, String title, String author, String description);
    List<Book> findByReader(String reader);
    Book save(Book book);
}
