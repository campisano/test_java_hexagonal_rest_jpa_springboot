package org.example.test_java_rest_jpa.application.usecases;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.dtos.BookDTOTranslator;
import org.example.test_java_rest_jpa.application.ports.in.CreateBookUseCasePort;
import org.example.test_java_rest_jpa.application.ports.out.BookPersistencePort;
import org.example.test_java_rest_jpa.domain.Book;

public class CreateBookUseCase implements CreateBookUseCasePort {

    private BookPersistencePort bookPersistence;

    public CreateBookUseCase(BookPersistencePort bookPersistence) {
        this.bookPersistence = bookPersistence;
    }

    @Override
    public BookDTO create(BookDTO bookDto) {
        Book book = BookDTOTranslator.fromDTO(bookDto);
        bookDto = BookDTOTranslator.toDTO(book);
        return bookPersistence.create(bookDto);
    }
}
