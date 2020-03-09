package org.example.test_java_rest_jpa.domain;

import java.util.Objects;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String description;
    private String reader;

    public Book(String isbn, String title, String author, String description, String reader) {
        ensureCreable(isbn, title, author, description, reader);
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
        this.reader = reader;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getReader() {
        return reader;
    }

    private static void ensureCreable(String isbn, String title, String author, String description, String reader) {
        Objects.requireNonNull(isbn, "Isbn cannot be null");
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(author, "Author cannot be null");
    }
}
