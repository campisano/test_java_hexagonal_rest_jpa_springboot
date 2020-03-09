package org.example.test_java_rest_jpa.adapters.repositories.book;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;

class BookModelTranslator {

    static BookModel fromDTO(BookDTO bookDTO) {
        return new BookModel(bookDTO.getIsbn(), bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getDescription(),
                bookDTO.getReader());
    }

    static BookDTO toDTO(final BookModel bookModel) {
        return new BookDTO(bookModel.getIsbn(), bookModel.getTitle(), bookModel.getAuthor(), bookModel.getDescription(),
                bookModel.getReader());
    }
}
