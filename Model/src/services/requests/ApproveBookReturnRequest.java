package services.requests;

public class ApproveBookReturnRequest extends RequestBase {
    private String userId;
    private int borrowId;

    public ApproveBookReturnRequest(String userId, int borrowId)
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
