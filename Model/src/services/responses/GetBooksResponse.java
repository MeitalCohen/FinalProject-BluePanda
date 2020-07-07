package services.responses;

import entities.BookStock;
import exceptions.BusinessException;

import java.util.List;

public class GetBooksResponse extends ResponseBase {
    private List<BookStock> books;

    public GetBooksResponse(List<BookStock> books) {
        this.buildResponse();
        this.books = books;
    }

    public GetBooksResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.books = null;
    }

    public List<BookStock> getBooks(){ return  this.books;}
}
