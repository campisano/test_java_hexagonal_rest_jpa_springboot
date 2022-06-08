package org.example.adapters.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.example.application.dtos.BookDTO;
import org.example.application.exceptions.AuthorInvalidException;
import org.example.application.exceptions.BookInvalidException;
import org.example.application.exceptions.IsbnAlreadyExistsException;
import org.example.application.exceptions.IsbnNotExistsException;
import org.example.application.ports.in.AddBookUseCasePort;
import org.example.application.ports.in.GetBookUseCasePort;
import org.example.application.ports.in.ListAllBooksUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HTTPBooksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPBooksController.class);

    private AddBookUseCasePort addBookUseCase;
    private GetBookUseCasePort getBookUseCase;
    private ListAllBooksUseCasePort listAllBooksUseCase;

    public HTTPBooksController(AddBookUseCasePort addBookUseCase, GetBookUseCasePort getBookUseCase,
            ListAllBooksUseCasePort listAllBooksUseCase) {
        this.addBookUseCase = addBookUseCase;
        this.getBookUseCase = getBookUseCase;
        this.listAllBooksUseCase = listAllBooksUseCase;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BookDTO> add(HttpServletRequest request, @RequestBody Optional<AddBookRequest> body) {
        LOGGER.info("method={}, path={}, body={}", request.getMethod(), request.getRequestURI(), body);

        if (!body.isPresent()) {
            LOGGER.error("request without body");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            var book = addBookUseCase.execute(
                    new BookDTO(body.get().isbn, body.get().title, body.get().authors, body.get().description));
            LOGGER.info("created, book={}", book);

            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        } catch (IsbnAlreadyExistsException | AuthorInvalidException | BookInvalidException exception) {
            LOGGER.error("exception, message={}", exception.getMessage());

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @RequestMapping(value = "{isbn}", method = RequestMethod.GET)
    public ResponseEntity<BookDTO> getByIsbn(HttpServletRequest request, @PathVariable String isbn) {
        LOGGER.info("method={}, path={}, isbn={}", request.getMethod(), request.getRequestURI(), isbn);

        try {
            var book = getBookUseCase.execute(isbn);
            LOGGER.info("ok, isbn={}", isbn);

            return ResponseEntity.ok(book);
        } catch (IsbnNotExistsException exception) {
            LOGGER.error("exception, message={}", exception.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> listAll(HttpServletRequest request) {
        LOGGER.info("method={}, path={}", request.getMethod(), request.getRequestURI());

        var books = listAllBooksUseCase.execute();
        LOGGER.info("ok");

        return ResponseEntity.ok(books);
    }
}

class AddBookRequest {
    public String isbn;
    public String title;
    public Set<String> authors;
    public String description;

    @Override
    public String toString() {
        return "AddBookRequest [isbn=" + isbn + ", title=" + title + ", authors=" + authors + ", description="
                + description + "]";
    }
}