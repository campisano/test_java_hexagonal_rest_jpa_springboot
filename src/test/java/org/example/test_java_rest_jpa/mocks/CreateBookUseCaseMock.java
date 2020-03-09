package org.example.test_java_rest_jpa.mocks;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.in.CreateBookUseCasePort;

public class CreateBookUseCaseMock implements CreateBookUseCasePort {

    public BookDTO createInput;
    public BookDTO createOutput;

    @Override
    public BookDTO create(BookDTO book) {
        createInput = book;
        return createOutput;
    }
}
