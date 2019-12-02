package org.example.test_java_rest_jpa.domain;

import java.util.List;

public interface BookRepository {

    List<Book> findByReader(String reader);
    Book save(Book book);
}
