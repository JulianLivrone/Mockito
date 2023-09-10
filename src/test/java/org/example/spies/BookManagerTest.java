package org.example.spies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookManagerTest {

    @InjectMocks
    private BookManager bookManager;

    // Spy let us mock some of the methods and execute for real some others
    // It is useful when the method of an object depends on two methods of the mock object, one we want to mock it and the other one run it for real
    // It is called Partial Mocking to use a Spy
    @Spy
    private BookService bookService;

    @Test
    public void testMockitoSpy(){
        // We need to mock findBook because it is communicating to database or not implemented
        // We need to call getAppliedDiscount to calculate the discounted price
        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        doReturn(book).when(bookService).findBook("1234");
//        when(bookService.findBook("1234")).thenReturn(book);
        // when().thenReturn() actually runs the method but in the end it returns what you tell it to return
        // doReturn().when() doesn't run the method, it automatically returns what you tell it to return
        int actualDiscount = bookManager.applyDiscountOnBook("1234", 10);
        assertEquals(450, actualDiscount);
    }
}
