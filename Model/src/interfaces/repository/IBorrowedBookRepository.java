package interfaces.repository;

import entities.BorrowedBook;

import java.util.Vector;

public interface IBorrowedBookRepository extends IRepository<BorrowedBook>{

    BorrowedBook update (BorrowedBook borrowedBook);

    BorrowedBook fetch(String userID, int bookID);

    BorrowedBook fetch(int borrowID);

    Vector<BorrowedBook> searchBorrowedBooksByID (int bookID);

    Vector<BorrowedBook> searchBorrowedBooksByUserID(String userID, int bookStatus);

}
