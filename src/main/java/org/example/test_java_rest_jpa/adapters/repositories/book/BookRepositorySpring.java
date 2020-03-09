package org.example.test_java_rest_jpa.adapters.repositories.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositorySpring extends JpaRepository<BookModel, Long> {

    List<BookModel> findByReader(String reader);

    Optional<BookModel> findByIsbn(String isbn);
}
