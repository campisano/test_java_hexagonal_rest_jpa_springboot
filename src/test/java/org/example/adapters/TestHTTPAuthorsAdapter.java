package org.example.adapters;

import java.util.Arrays;

import org.example.TestUtils;
import org.example.application.dtos.AuthorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DirtiesContext
public class TestHTTPAuthorsAdapter {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void post() throws Exception {
        // Act
        var author = new AuthorDTO("author1");
        var response = TestUtils.JsonRequest().body(author).post("/v1/authors");

        // Assert
        Assertions.assertEquals(201, response.getStatusCode());
        Assertions.assertEquals(TestUtils.toJson(author), TestUtils.toJson(response.jsonPath().prettify()));
    }

    @Test
    public void postAlreadyExistent() {
        // Arrange
        var a1 = new AuthorDTO("author1");
        var a2 = new AuthorDTO("author2");
        Arrays.asList(a1, a2).forEach(a -> {
            TestUtils.JsonRequest().body(a).post("/v1/authors");
        });

        // Act
        var author = new AuthorDTO("author1");
        var response = TestUtils.JsonRequest().body(author).post("/v1/authors");

        // Assert
        Assertions.assertEquals(422, response.getStatusCode());
        Assertions.assertEquals("", response.getBody().asString());
    }
}
