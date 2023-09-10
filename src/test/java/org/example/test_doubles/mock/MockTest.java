package org.example.test_doubles.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

public class MockTest {

    @Test
    public void demoMock(){
        BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        BookService bookService = new BookService(bookRepositoryMock);

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit In Action", 400, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        bookRepositoryMock.verify(book2, 2);
    }

    @Test
    public void demoMockWithMockito(){
        BookRepository bookRepositoryMock = mock(BookRepository.class);
        BookService bookService = new BookService(bookRepositoryMock);

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "JUnit In Action", 400, LocalDate.now());

        bookService.addBook(book1);
        bookService.addBook(book2);

        Mockito.verify(bookRepositoryMock, Mockito.times(1)).save(book1);
        Mockito.verify(bookRepositoryMock).save(book2);
    }
}
