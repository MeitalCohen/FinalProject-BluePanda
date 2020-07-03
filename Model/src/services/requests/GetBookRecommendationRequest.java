package services.requests;

public class GetBookRecommendationRequest extends RequestBase {
    private int bookId;

    public GetBookRecommendationRequest(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId(){return this.bookId;}
}
