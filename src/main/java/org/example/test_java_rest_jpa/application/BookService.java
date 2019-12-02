package org.example.test_java_rest_jpa.application;

import java.util.List;
import org.example.test_java_rest_jpa.domain.Book;
import org.example.test_java_rest_jpa.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
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
