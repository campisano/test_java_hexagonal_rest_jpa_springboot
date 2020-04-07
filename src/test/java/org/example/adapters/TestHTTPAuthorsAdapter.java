package org.example.adapters;

import java.util.Arrays;
import java.util.List;

import org.example.application.dtos.AuthorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class TestHTTPAuthorsAdapter {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void post() {
        AuthorDTO requestBody = new AuthorDTO("name1");

        ResponseEntity<DeserializableAuthorDTO> response = rest.postForEntity("/v1/authors", requestBody,
                DeserializableAuthorDTO.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(requestBody.toString(), response.getBody().toString());
    }

    @Test
    public void postWhenAlreadyExist() {
        AuthorDTO a1 = new AuthorDTO("name1");
        AuthorDTO a2 = new AuthorDTO("name2");
        postAuthors(Arrays.asList(a1, a2));

        AuthorDTO requestBody = new AuthorDTO("name1");

        ResponseEntity<DeserializableAuthorDTO> response = rest.postForEntity("/v1/authors", requestBody,
                DeserializableAuthorDTO.class);

        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    private void postAuthors(List<AuthorDTO> authors) {
        authors.forEach(author -> {
            rest.postForEntity("/v1/authors", author, DeserializableAuthorDTO.class);
        });
    }
}

class DeserializableAuthorDTO extends AuthorDTO {
    public DeserializableAuthorDTO() {
        super(null);
    }
}