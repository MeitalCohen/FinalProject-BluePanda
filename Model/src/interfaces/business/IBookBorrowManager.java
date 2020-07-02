package interfaces.business;

import entities.BookStock;
import entities.BorrowedBook;
import exceptions.BusinessException;

public interface IBookBorrowManager {

    BorrowedBook extendBookBorrowing(String userId, BookStock bookStock) throws BusinessException;

    BorrowedBook borrowBook(String userId, BookStock book) throws BusinessException;

    BorrowedBook getBookBorrowInformation(String userId, int bookID);

    BorrowedBook returnBook (String userId, int borrowID) throws BusinessException;

    BorrowedBook approveBookReturn(String userId, int borrowID) throws BusinessException;
}
