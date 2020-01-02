package org.example.test_java_rest_jpa.application;

import java.util.List;
import org.example.test_java_rest_jpa.domain.Book;
import org.example.test_java_rest_jpa.domain.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(
        String isbn, String title, String author, String description) {

        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);

        return book;
    }

    public List<Book> findByReader(String reader) {
        return bookRepository.findByReader(reader);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
