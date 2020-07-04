package services.requests;

import enums.BooksFilter;

public class GetBooksRequest extends RequestBase {
    private BooksFilter filter;

    public GetBooksRequest(BooksFilter filter)
    {
        this.filter = filter;
    }

    public BooksFilter getFilter(){return filter;}
}
