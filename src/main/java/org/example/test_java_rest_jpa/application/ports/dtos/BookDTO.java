package org.example.test_java_rest_jpa.application.ports.dtos;

import java.util.Objects;

public class BookDTO {

    private String isbn;
    private String title;
    private String author;
    private String description;
    private String reader;

    public BookDTO() {
    }

    public BookDTO(String isbn, String title, String author, String description, String reader) {
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

    @Override
    public int hashCode() {
        return Objects.hash(author, description, isbn, reader, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookDTO other = (BookDTO) obj;
        return Objects.equals(author, other.author) && Objects.equals(description, other.description)
                && Objects.equals(isbn, other.isbn) && Objects.equals(reader, other.reader)
                && Objects.equals(title, other.title);
    }
}
