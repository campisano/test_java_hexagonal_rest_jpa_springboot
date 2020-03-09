package org.example.test_java_rest_jpa.mocks;

import java.util.List;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.out.BookPersistencePort;

public class BookPersistenceMock implements BookPersistencePort {
    public String findByReaderInput;
    public List<BookDTO> findByReaderOutput;
    public BookDTO createInput;
    public BookDTO createOutput;
    public BookDTO updateByIsbnInput;
    public BookDTO updateByIsbnOutput;

    @Override
    public List<BookDTO> findByReader(String reader) {
        findByReaderInput = reader;
        return findByReaderOutput;
    }

    @Override
    public BookDTO create(BookDTO book) {
        createInput = book;
        return createOutput;
    }

    @Override
    public BookDTO updateByIsbn(BookDTO book) {
        updateByIsbnInput = book;
        return updateByIsbnOutput;
    }
}
