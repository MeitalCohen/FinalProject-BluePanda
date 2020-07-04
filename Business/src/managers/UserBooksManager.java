package managers;

import entities.BookStock;
import entities.BorrowedBook;
import entities.UserLending;
import enums.BorrowStatus;
import exceptions.BusinessException;
import exceptions.GeneralErrorException;
import interfaces.business.IUserBooksManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;

import java.util.Vector;
import java.util.stream.Collectors;

public class UserBooksManager implements IUserBooksManager {
    private IBookStockRepository bookStockRepository;
    private IBorrowedBookRepository borrowedBookRepository;

    public UserBooksManager(IBookStockRepository bookStockRepository, IBorrowedBookRepository borrowedBookRepository)
    {
        this.bookStockRepository = bookStockRepository;
        this.borrowedBookRepository = borrowedBookRepository;
    }

    public Vector<UserLending> getAllUserActiveBorrowing(String userId) throws BusinessException
    {
        Vector<UserLending> userLendings = new Vector<>();

        Vector<BorrowedBook> borrowing = borrowedBookRepository.getAllBorrowed();

        if (borrowing == null)
            return new Vector<>();

        Vector<BorrowedBook> filteredBorrow = borrowing.stream().filter(bookTemp ->
                (bookTemp.getStatus() == BorrowStatus.Borrowed.StatusValue()
                        || bookTemp.getStatus() == BorrowStatus.WaitingForReturnApproval.StatusValue())
                && bookTemp.getUserID().equals(userId))
                .collect(Collectors.toCollection(() -> new Vector<BorrowedBook>()));

        if (filteredBorrow == null)
            return new Vector<>();

        for (BorrowedBook borrow: filteredBorrow)
        {
            BookStock book = this.bookStockRepository.fetch(borrow.getBookID()) ;

            if (book == null)
                throw new GeneralErrorException();

            UserLending userLending = new UserLending(borrow.getBorrowID(), book.getId(), book.getBookName(), book.getAuthorName(),
                    book.getCategory(), borrow.isExtended(), borrow.getStartBorrowRequest(), borrow.getFinalBorrowDate(), borrow.getStatus());

            userLendings.add(userLending);
        }
        return  userLendings;

    }
}
