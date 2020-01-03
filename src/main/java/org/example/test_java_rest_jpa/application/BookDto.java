package org.example.test_java_rest_jpa.application;

public class BookDto {

    private String isbn;
    private String title;
    private String author;
    private String description;

    public BookDto() {
    }

    public BookDto(
        String isbn, String title, String author, String description) {

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.description = description;
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
}
