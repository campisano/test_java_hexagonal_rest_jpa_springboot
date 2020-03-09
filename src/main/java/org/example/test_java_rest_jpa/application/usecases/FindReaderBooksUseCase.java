package org.example.test_java_rest_jpa.application.usecases;

import java.util.List;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.in.FindReaderBooksUseCasePort;
import org.example.test_java_rest_jpa.application.ports.out.BookPersistencePort;

public class FindReaderBooksUseCase implements FindReaderBooksUseCasePort {

    private BookPersistencePort bookPersistence;

    public FindReaderBooksUseCase(BookPersistencePort bookPersistence) {
        this.bookPersistence = bookPersistence;
    }

    @Override
    public List<BookDTO> findByReader(String reader) {
        return bookPersistence.findByReader(reader);
    }
}
