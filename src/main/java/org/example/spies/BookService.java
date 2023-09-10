package org.example.spies;

import org.example.stubbing.BookRepository;
import org.example.stubbing.BookRequest;

import java.util.List;

public class BookService {

    public Book findBook(String bookId) {
        throw new RuntimeException("Method not implemented");
    }

    public int getAppliedDiscount(Book book, int discountRate) {
        int price = book.getPrice();
        int newPrice = price - (price * discountRate / 100);
        return newPrice;
    }
}
