package org.example.bdd.behavior.verification;

public interface BookRepository {

    void save(Book book);

    Book findBookById(String bookId);
}
