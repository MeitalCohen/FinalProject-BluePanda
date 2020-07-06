package repositoriesDummy;

import businessTest.TestState;
import entities.BorrowedBook;
import entities.User;
import exceptions.BusinessException;
import interfaces.repository.IBorrowedBookRepository;

import java.util.Vector;

public class BorrowedBookRepository implements IBorrowedBookRepository {
    private BorrowedBook borrowedBook;
    private TestState testState;

    public void setup(BorrowedBook borrowedBook, TestState state)
    {
        this.borrowedBook = borrowedBook;
        this.testState = state;
    }

    @Override
    public BorrowedBook update(BorrowedBook borrowedBook) {
        return this.borrowedBook;
    }

    @Override
    public BorrowedBook fetch(String userID, String bookID) {
        return this.borrowedBook;
    }

    @Override
    public BorrowedBook fetch(String borrowID) {
        return borrowedBook;
    }

    @Override
    public Vector<BorrowedBook> searchBorrowedBooksByID(String bookID) {
        Vector<BorrowedBook> users = new Vector<>();
        users.add(this.borrowedBook);
        return users;    }

    @Override
    public Vector<BorrowedBook> searchBorrowedBooksByUserID(String userID, int bookStatus) {
        Vector<BorrowedBook> users = new Vector<>();
        users.add(this.borrowedBook);
        return users;
    }

    @Override
    public Vector<BorrowedBook> getAllBorrowed() {
        return null;
    }

    @Override
    public BorrowedBook insert(BorrowedBook borrowedBook) throws BusinessException {
        return null;
    }
}
