package org.example.behavior.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

//    @Mock
//    private BookRepository bookRepository;

    @Spy
    private BookRepository bookRepository;

    @Test
    public void testAddBook(){
        Book book = new Book(null, "Mockito In Action", 550, LocalDate.now());
        bookService.addBook(book);
        verify(bookRepository).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice(){
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
        bookService.addBook(bookRequest);
        // Both are the same
        verify(bookRepository, never()).save(book);
        verify(bookRepository, times(0)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice1(){
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        // Both are the same
        verify(bookRepository).save(book);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice2(){
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        verify(bookRepository, times(2)).save(book);
    }

    @Test
    public void testUpdatePrice(){
        bookService.updatePrice(null, 600);
        // Checks that the object hasn't got any interactions in any of its methods, basically that it has no interactions
        verifyNoInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice2(){
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);
        verify(bookRepository).findBookById("1234");
        verify(bookRepository).save(book);
        // Checks if the object has no more interactions besides the ones we verified
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice3(){
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);
        // We use the inOrder object to check that the methods we are verifying were called in the order we verified them
        InOrder inOrder = inOrder(bookRepository);
        inOrder.verify(bookRepository).findBookById("1234");
        inOrder.verify(bookRepository).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice3(){
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
        verify(bookRepository, atLeast(1)).save(book);
        verify(bookRepository, atMost(5)).save(book);
//        verify(bookRepository, atMostOnce()).save(book);
        verify(bookRepository, atLeastOnce()).save(book);

    }
}
