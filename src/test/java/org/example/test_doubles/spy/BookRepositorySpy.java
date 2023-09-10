package org.example.test_doubles.spy;

public class BookRepositorySpy implements BookRepository {

    int saveCalled = 0;
    Book lastAddedBook;

    @Override
    public void save(Book book) {
        saveCalled++;
        lastAddedBook = book;
    }

    public int timesCalled(){
        return saveCalled;
    }

    public boolean calledWith(Book book){
        return lastAddedBook.equals(book);
    }
}
