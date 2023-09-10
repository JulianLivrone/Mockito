package org.example.behavior.verification;

public interface BookRepository {

    void save(Book book);

    Book findBookById(String bookId);
}
