package services.requests;

import entities.BookStock;
import entities.BorrowedBook;

public class BookLendingRequest extends RequestBase{
    private String userId;
    private BookStock bookStock;

    public BookLendingRequest(String userId, BookStock bookStock)
    {
        this.userId = userId;
        this.bookStock = bookStock;
    }

    public String getUserId() {
        return userId;
    }

    public BookStock getBookStock() {
        return bookStock;
    }
}
