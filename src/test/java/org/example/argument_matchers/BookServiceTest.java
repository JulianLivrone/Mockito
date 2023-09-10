package org.example.argument_matchers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

//    @Spy
//    private BookRepository bookRepository;

    @Test
    public void testUpdatePrice(){
        Book book1 = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        Book book2 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        // Both work
        when(bookRepository.findBookById(any())).thenReturn(book1);
        when(bookRepository.findBookById(any(String.class))).thenReturn(book1);
        bookService.updatePrice("1234", 500);
        verify(bookRepository).save(book2);
    }

    // Argument Matchers should be provided for all arguments!!!
    // Argument Matchers can't be used outside stubbing/verification

    @Test
    public void testInvalidUseOfArgumentMatchers(){
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
//        when(bookRepository.findBookByTitleAndPublishedDate("Mockito In Action", LocalDate.now())).thenReturn(book);
        // This won't work
//        when(bookRepository.findBookByTitleAndPublishedDate("Mockito In Action", any())).thenReturn(book);
        // Now it works, with the eq() argument matcher
        when(bookRepository.findBookByTitleAndPublishedDate(eq("Mockito In Action"), any())).thenReturn(book);
        // This won't work
//        Book actualBook = bookService.getBookByTitleAndPublishedDate(eq("Mockito In Action"), any());
        Book actualBook = bookService.getBookByTitleAndPublishedDate("Mockito In Action", LocalDate.now());
        assertEquals("Mockito In Action", actualBook.getTitle());
    }

    @Test
    public void testSpecificTypeOfArgumentMatchers(){
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        when(bookRepository.findBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, true)).thenReturn(book);
        //All combinations work
//        when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(), anyInt(), anyBoolean())).thenReturn(book);
//        when(bookRepository.findBookByTitleAndPriceAndIsDigital(any(String.class), any(Integer.class), any(Boolean.class))).thenReturn(book);
//        when(bookRepository.findBookByTitleAndPriceAndIsDigital(any(String.class), any(Integer.class), anyBoolean())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, true);
        assertEquals("Mockito In Action", actualBook.getTitle());
    }

    @Test
    public void testCollectionTypeArgumentMatchers(){
        List<Book> books = new ArrayList<>();
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
        books.add(book);
        bookService.addBooks(books);
//        verify(bookRepository).saveAll(books);
        verify(bookRepository).saveAll(anyList()); // anySet, anyMap, anyCollection...
    }

    @Test
    public void testSringTypeOfArgumentMatchers(){
        Book book = new Book("1234", "Mockito In Action", 600, LocalDate.now());
//        when(bookRepository.findBookByTitleAndPriceAndIsDigital(anyString(), anyInt(), anyBoolean())).thenReturn(book);
//        when(bookRepository.findBookByTitleAndPriceAndIsDigital(startsWith("Mockito"), anyInt(), anyBoolean())).thenReturn(book);
//        when(bookRepository.findBookByTitleAndPriceAndIsDigital(endsWith("Action"), anyInt(), anyBoolean())).thenReturn(book);
        when(bookRepository.findBookByTitleAndPriceAndIsDigital(contains("In"), anyInt(), anyBoolean())).thenReturn(book);
        Book actualBook = bookService.getBookByTitleAndPriceAndIsDigital("Mockito In Action", 600, true);
        assertEquals("Mockito In Action", actualBook.getTitle());
    }
}
