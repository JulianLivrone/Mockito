package org.example.argument_matchers;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String bookId;
    private String title;
    private int price;
    private LocalDate published;
    private boolean isDigital;

    public Book(){

    }

    public Book(String bookId, String title, int price, LocalDate published) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.published = published;
    }

    public Book(String bookId, String title, int price, LocalDate published, boolean isDigital) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.published = published;
        this.isDigital = isDigital;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public boolean isDigital() {
        return isDigital;
    }

    public void setDigital(boolean digital) {
        isDigital = digital;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return price == book.price && isDigital == book.isDigital && Objects.equals(title, book.title) && Objects.equals(published, book.published);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, published, isDigital);
    }
}
