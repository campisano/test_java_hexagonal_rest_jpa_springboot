package org.example.test_java_rest_jpa.adapters.controllers;

import java.util.List;

import org.example.test_java_rest_jpa.application.ports.dtos.BookDTO;
import org.example.test_java_rest_jpa.application.ports.in.CreateBookUseCasePort;
import org.example.test_java_rest_jpa.application.ports.in.FindReaderBooksUseCasePort;
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

    private CreateBookUseCasePort createBookUseCase;
    private FindReaderBooksUseCasePort findReaderBooksUseCase;

    @Autowired
    public ReadingListController(CreateBookUseCasePort createBookUseCase,
            FindReaderBooksUseCasePort findReaderBooksUseCase) {
        this.createBookUseCase = createBookUseCase;
        this.findReaderBooksUseCase = findReaderBooksUseCase;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> readersBooks(@PathVariable("reader") String reader) {

        List<BookDTO> books = findReaderBooksUseCase.findByReader(reader);

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public ResponseEntity<BookDTO> addToReadingList(@PathVariable("reader") String reader,
            @RequestBody BookDTO requestBody) {

        BookDTO book = new BookDTO(requestBody.getIsbn(), requestBody.getTitle(), requestBody.getAuthor(),
                requestBody.getDescription(), reader);
        book = createBookUseCase.create(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
}
