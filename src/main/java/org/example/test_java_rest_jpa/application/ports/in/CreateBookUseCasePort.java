package org.example.test_java_rest_jpa.application.ports.in;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;

public interface CreateBookUseCasePort {
    public BookDTO create(BookDTO book);
}
