package org.example.adapters.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.example.application.dtos.AuthorDTO;
import org.example.application.exceptions.AuthorAlreadyExistsException;
import org.example.application.exceptions.AuthorInvalidException;
import org.example.application.ports.in.AddAuthorUseCasePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/authors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HTTPAuthorsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HTTPAuthorsController.class);

    private AddAuthorUseCasePort addAuthorUseCase;

    public HTTPAuthorsController(AddAuthorUseCasePort addAuthorUseCase) {
        this.addAuthorUseCase = addAuthorUseCase;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<AuthorDTO> add(HttpServletRequest request, @RequestBody Optional<AddAuthorRequest> body) {
        LOGGER.info("method={}, path={}, body={}", request.getMethod(), request.getRequestURI(), body);

        if (!body.isPresent()) {
            LOGGER.error("request without body");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        try {
            AuthorDTO author = addAuthorUseCase.execute(new AuthorDTO(body.get().name));
            LOGGER.info("created, author={}", author);

            return ResponseEntity.status(HttpStatus.CREATED).body(author);
        } catch (AuthorInvalidException | AuthorAlreadyExistsException exception) {
            LOGGER.error("exception, message={}", exception.getMessage());

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}

class AddAuthorRequest {
    public String name;

    @Override
    public String toString() {
        return "AddAuthorRequest [name=" + name + "]";
    }
}