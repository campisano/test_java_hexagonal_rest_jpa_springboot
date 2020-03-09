package org.example.test_java_rest_jpa.application.ports.in;

import java.util.List;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;

public interface FindReaderBooksUseCasePort {
    List<BookDTO> findByReader(String reader);
}
