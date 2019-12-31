package org.example.test_java_rest_jpa.infrastructure;

import java.util.List;
import org.example.test_java_rest_jpa.domain.Book;
import org.example.test_java_rest_jpa.domain.BookRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositoryJpa
    extends BookRepository, JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
