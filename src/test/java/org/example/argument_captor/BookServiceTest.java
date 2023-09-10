package org.example.argument_captor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Captor // Allows us to not have to create the instance of the argument captor
    private ArgumentCaptor<Book> bookArgumentCaptor;

    @Test
    public void testSaveBook(){
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        // We use the argument captor to get the arguments used when calling our mocked object's methods
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        bookService.addBook(bookRequest);
        verify(bookRepository).save(bookArgumentCaptor.capture());
        Book book = bookArgumentCaptor.getValue();
        assertEquals("Mockito In Action", book.getTitle());
    }

    @Test
    public void testSaveBook2(){
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        bookService.addBook(bookRequest);
        verify(bookRepository).save(bookArgumentCaptor.capture());
        Book book = bookArgumentCaptor.getValue();
        assertEquals("Mockito In Action", book.getTitle());
    }
}
