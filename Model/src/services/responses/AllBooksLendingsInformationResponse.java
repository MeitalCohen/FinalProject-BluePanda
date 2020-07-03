package services.responses;

import entities.BorrowedBook;
import exceptions.BusinessException;

import java.util.Vector;

public class AllBooksLendingsInformationResponse extends ResponseBase {
    private Vector<BorrowedBook> borrowedBooks;

    public AllBooksLendingsInformationResponse(Vector<BorrowedBook> borrowedBooks)
    {
        this.buildResponse();
        this.borrowedBooks = borrowedBooks;
    }

    public AllBooksLendingsInformationResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.borrowedBooks = null;
    }

    public Vector<BorrowedBook> getBorrowedBook() {
        return borrowedBooks;
    }}
