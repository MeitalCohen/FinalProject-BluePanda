package managers;

import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;

public class BookReportManager {

    private IBookStockRepository bookStockRepository;
    private IBorrowedBookRepository borrowedBookRepository;

    public BookReportManager(IBookStockRepository bookStockRepository, IBorrowedBookRepository borrowedBookRepository)
    {
        this.bookStockRepository = bookStockRepository;
        this.borrowedBookRepository = borrowedBookRepository;
    }

    public void getMaxBorrowedBooks()
    {

    }

    public void getMostCommonInStockCategory()
    {

    }

    public void getMostRecentPurchasedBooks()
    {

    }

    public void getBookStatusByBookName()
    {

    }
}
