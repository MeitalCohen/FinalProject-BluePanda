package services.requests;

import entities.BookStock;

public class ExtendLendingRequest extends RequestBase{
    private String userId;
    private BookStock book;

    public ExtendLendingRequest(String userId, BookStock book)
    {
        this.userId = userId;
        this.book = book;
    }

    public String getUserId() {
        return userId;
    }

    public BookStock getBook() {
        return book;
    }
}
