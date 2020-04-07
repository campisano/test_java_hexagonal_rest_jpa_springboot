package org.example.adapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.example.application.dtos.AuthorDTO;
import org.example.application.dtos.BookDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class TestHTTPBooksAdapter {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void listAllWhenEmpty() {

        ResponseEntity<List<DeserializableBookDTO>> response = rest.exchange("/v1/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DeserializableBookDTO>>() {
                });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(new ArrayList<BookDTO>(), response.getBody());
    }

    @Test
    public void listAllWhenExists() {
        postAuthors(Arrays.asList(new AuthorDTO("author1")));
        postAuthors(Arrays.asList(new AuthorDTO("author2")));
        BookDTO b1 = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList("author1")), "description1");
        BookDTO b2 = new BookDTO("isbn2", "title2", new HashSet<>(Arrays.asList("author2")), "description2");
        postBooks(Arrays.asList(b1, b2));

        ResponseEntity<List<DeserializableBookDTO>> response = rest.exchange("/v1/books", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<DeserializableBookDTO>>() {
                });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals(response.getBody().get(0).toString(), b1.toString());
        Assertions.assertEquals(response.getBody().get(1).toString(), b2.toString());
    }

    @Test
    public void getOneWhenEmpty() {
        ResponseEntity<DeserializableBookDTO> response = rest.exchange("/v1/books/unexistent_isbn", HttpMethod.GET,
                null, new ParameterizedTypeReference<DeserializableBookDTO>() {
                });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void getOneWhenExist() {
        postAuthors(Arrays.asList(new AuthorDTO("author1")));
        postAuthors(Arrays.asList(new AuthorDTO("author2")));
        BookDTO b1 = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList("author1")), "description1");
        BookDTO b2 = new BookDTO("isbn2", "title2", new HashSet<>(Arrays.asList("author2")), "description2");
        postBooks(Arrays.asList(b1, b2));

        ResponseEntity<DeserializableBookDTO> response = rest.exchange("/v1/books/isbn1", HttpMethod.GET, null,
                new ParameterizedTypeReference<DeserializableBookDTO>() {
                });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(b1.toString(), response.getBody().toString());
    }

    @Test
    public void post() {
        postAuthors(Arrays.asList(new AuthorDTO("author1")));
        BookDTO requestBody = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList("author1")), "description1");
        HttpEntity<?> request = new HttpEntity<>(requestBody);

        ResponseEntity<DeserializableBookDTO> response = rest.exchange("/v1/books", HttpMethod.POST, request,
                new ParameterizedTypeReference<DeserializableBookDTO>() {
                });

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(requestBody.toString(), response.getBody().toString());
    }

    @Test
    public void postWhenAlreadyExist() {
        postAuthors(Arrays.asList(new AuthorDTO("author1")));
        BookDTO b1 = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList("author1")), "description1");
        BookDTO b2 = new BookDTO("isbn2", "title2", new HashSet<>(Arrays.asList("author2")), "description2");
        postBooks(Arrays.asList(b1, b2));

        BookDTO requestBody = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList("author1")), "description1");
        HttpEntity<?> request = new HttpEntity<>(requestBody);

        ResponseEntity<DeserializableBookDTO> response = rest.exchange("/v1/books", HttpMethod.POST, request,
                new ParameterizedTypeReference<DeserializableBookDTO>() {
                });

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    private void postAuthors(List<AuthorDTO> authors) {
        authors.forEach(author -> {
            rest.postForEntity("/v1/authors", author, DeserializableAuthorDTO.class);
        });
    }

    private void postBooks(List<BookDTO> books) {
        books.forEach(book -> {
            rest.postForEntity("/v1/books", book, DeserializableBookDTO.class);
        });
    }
}

class DeserializableBookDTO extends BookDTO {
    public DeserializableBookDTO() {
        super(null, null, null, null);
    }
}