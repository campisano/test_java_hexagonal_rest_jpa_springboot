package org.example.test_java_rest_jpa.mocks;

import java.util.List;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.in.FindReaderBooksUseCasePort;

public class FindReaderBooksUseCaseMock implements FindReaderBooksUseCasePort {

    public String findByReaderInput;
    public List<BookDTO> findByReaderOutput;

    @Override
    public List<BookDTO> findByReader(String reader) {
        findByReaderInput = reader;
        return findByReaderOutput;
    }
}
