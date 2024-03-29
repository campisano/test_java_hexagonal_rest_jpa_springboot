package org.example.adapters;

import java.util.Arrays;
import java.util.HashSet;

import org.example.TestUtils;
import org.example.application.dtos.AuthorDTO;
import org.example.application.dtos.BookDTO;
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
public class TestHTTPBooksAdapter {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void listAllWhenEmpty() {
        // Act
        var response = TestUtils.JsonRequest().get("/v1/books");

        // Assert
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("[]", response.getBody().asString());
    }

    @Test
    public void listAllWhenExists2() throws Exception {
        // Arrange
        var a1 = new AuthorDTO("author1");
        var a2 = new AuthorDTO("author2");
        Arrays.asList(a1, a2).forEach(a -> {
            TestUtils.JsonRequest().body(a).post("/v1/authors");
        });
        var b1 = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList(a1.getName())), "description1");
        var b2 = new BookDTO("isbn2", "title2", new HashSet<>(Arrays.asList(a2.getName())), "description2");
        Arrays.asList(b1, b2).forEach(b -> {
            TestUtils.JsonRequest().body(b).post("/v1/books");
        });

        // Act
        var response = TestUtils.JsonRequest().get("/v1/books");

        // Assert
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertNotNull(response.jsonPath().getString(""));
        Assertions.assertEquals(TestUtils.toJson(Arrays.asList(b1, b2)),
                TestUtils.toJson(response.jsonPath().prettify()));
    }

    @Test
    public void getOneWhenEmpty() {
        // Act
        var response = TestUtils.JsonRequest().get("/v1/books/unexistent_isbn");

        // Assert
        Assertions.assertEquals(404, response.getStatusCode());
        Assertions.assertEquals("", response.getBody().asString());
    }

    @Test
    public void getOneWhenExist() throws Exception {
        // Arrange
        var a1 = new AuthorDTO("author1");
        var a2 = new AuthorDTO("author2");
        Arrays.asList(a1, a2).forEach(a -> {
            TestUtils.JsonRequest().body(a).post("/v1/authors");
        });
        var b1 = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList(a1.getName())), "description1");
        var b2 = new BookDTO("isbn2", "title2", new HashSet<>(Arrays.asList(a2.getName())), "description2");
        Arrays.asList(b1, b2).forEach(b -> {
            TestUtils.JsonRequest().body(b).post("/v1/books");
        });

        // Act
        var response = TestUtils.JsonRequest().get("/v1/books/isbn1");

        // Assert
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(TestUtils.toJson(b1), TestUtils.toJson(response.jsonPath().prettify()));
    }

    @Test
    public void post() throws Exception {
        // Arrange
        var author = new AuthorDTO("author1");
        TestUtils.JsonRequest().body(author).post("/v1/authors");

        // Act
        var book = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList(author.getName())), "description1");
        var response = TestUtils.JsonRequest().body(book).post("/v1/books");

        // Assert
        Assertions.assertEquals(201, response.getStatusCode());
        Assertions.assertEquals(TestUtils.toJson(book), TestUtils.toJson(response.jsonPath().prettify()));
    }

    @Test
    public void postWhenAlreadyExist() {
        // Arrange
        var a1 = new AuthorDTO("author1");
        var a2 = new AuthorDTO("author2");
        Arrays.asList(a1, a2).forEach(a -> {
            TestUtils.JsonRequest().body(a).post("/v1/authors");
        });
        var b1 = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList(a1.getName())), "description1");
        var b2 = new BookDTO("isbn2", "title2", new HashSet<>(Arrays.asList(a2.getName())), "description2");
        Arrays.asList(b1, b2).forEach(b -> {
            TestUtils.JsonRequest().body(b).post("/v1/books");
        });

        // Act
        var book = new BookDTO("isbn1", "title1", new HashSet<>(Arrays.asList(a1.getName())), "description1");
        var response = TestUtils.JsonRequest().body(book).post("/v1/books");

        // Assert
        Assertions.assertEquals(422, response.getStatusCode());
        Assertions.assertEquals("", response.getBody().asString());
    }
}