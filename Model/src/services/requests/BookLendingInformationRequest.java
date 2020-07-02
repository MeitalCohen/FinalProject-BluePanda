package services.requests;

public class BookLendingInformationRequest extends RequestBase{
    private String userId;
    private int bookId;

    public BookLendingInformationRequest(String userId, int bookId)
    {
        this.userId = userId;
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }
}
