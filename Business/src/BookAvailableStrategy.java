import entities.BookStock;
import entities.BorrowedBook;
import interfaces.IBookAvailableStrategy;
import interfaces.repository.IBorrowedBookRepository;

import java.util.Vector;

public class BookAvailableStrategy implements IBookAvailableStrategy {

    private IBorrowedBookRepository _borrowedBookRepository;

    public BookAvailableStrategy(IBorrowedBookRepository borrowedBookRepository)
    {
        _borrowedBookRepository = borrowedBookRepository;
    }

    public boolean isBookAvailableToBorrow(BookStock book)
    {
        Vector<BorrowedBook> borrowedBooks = _borrowedBookRepository.searchBorrowedBooksByID(book.getId());

        if (borrowedBooks == null && book.getQuantity() > 0)
            return true;

        if (borrowedBooks.capacity() < book.getQuantity())
            return true;

        return false;
    }
}
