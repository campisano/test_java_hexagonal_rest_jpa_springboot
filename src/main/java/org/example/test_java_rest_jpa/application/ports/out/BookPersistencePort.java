package org.example.test_java_rest_jpa.application.ports.out;

import java.util.List;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;

public interface BookPersistencePort {

    List<BookDTO> findByReader(String reader);

    BookDTO create(BookDTO book);

    BookDTO updateByIsbn(BookDTO book);
}
