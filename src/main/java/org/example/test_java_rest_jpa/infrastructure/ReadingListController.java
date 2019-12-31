package org.example.test_java_rest_jpa.infrastructure;

import java.util.List;
import org.example.test_java_rest_jpa.application.BookDto;
import org.example.test_java_rest_jpa.application.BookDtoFactory;
import org.example.test_java_rest_jpa.application.BookService;
import org.example.test_java_rest_jpa.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/readingList/")
public class ReadingListController {

    private BookService bookService;

    @Autowired
    public ReadingListController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> readersBooks(
        @PathVariable("reader") String reader) {

        List<Book> readingList = bookService.findByReader(reader);
        List<BookDto> responseBody = BookDtoFactory.translate(readingList);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public ResponseEntity<BookDto> addToReadingList(
        @PathVariable("reader") String reader,
        @RequestBody BookDto requestBody) {

        Book book = bookService.create(
            requestBody.getIsbn(),
            requestBody.getTitle(),
            requestBody.getAuthor(),
            requestBody.getDescription());
        book.setReader(reader);
        bookService.save(book);
        BookDto responseBody = BookDtoFactory.translate(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
