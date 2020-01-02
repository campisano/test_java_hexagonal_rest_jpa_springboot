package org.example.test_java_rest_jpa.infrastructure;

import org.example.test_java_rest_jpa.application.BookService;
import org.example.test_java_rest_jpa.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceSpring extends BookService {

    @Autowired
    public BookServiceSpring(BookRepository bookRepository) {
        super(bookRepository);
    }
}
