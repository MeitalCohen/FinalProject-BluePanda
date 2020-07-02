package services.requests;

public class ReturnBookRequest extends RequestBase {
    private String userId;
    private int borrowId;

    public ReturnBookRequest(String userId, int borrowId)
    {
        this.userId = userId;
        this.borrowId = borrowId;
    }

    public String getUserId() {
        return userId;
    }

    public int getBorrowId() {
        return borrowId;
    }
}
