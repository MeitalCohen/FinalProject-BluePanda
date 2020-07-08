package managers;

import entities.BookStock;
import enums.BooksFilter;
import interfaces.IBookAvailableStrategy;
import interfaces.business.IBooksManager;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;
import strategy.BookAvailableStrategy;

import java.util.List;
import java.util.Vector;

public class BooksManager implements IBooksManager {
    private IBookStockRepository bookStockRepository;
    private IBorrowedBookRepository borrowedBookRepository;
    private IBookAvailableStrategy bookAvailableStrategy;

    public BooksManager(IBookStockRepository bookStockRepository, IBorrowedBookRepository borrowedBookRepository)
    {
        this.bookStockRepository = bookStockRepository;
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookAvailableStrategy = new BookAvailableStrategy(borrowedBookRepository);
    }

    public Vector<BookStock> getBooksByFilter(BooksFilter filter)
    {
        if (filter == BooksFilter.All)
        {
            return this.bookStockRepository.searchByName(null);
        }
        else {
            Vector<BookStock> books = this.bookStockRepository.searchByName(null);
            if (books != null) {
                Vector<BookStock> booksAvailable = new Vector<>();
                for (BookStock book : books) {
                    boolean isAvailable = this.bookAvailableStrategy.isBookAvailableToBorrow(book);
                    if (isAvailable)
                        booksAvailable.add(book);
                }
                return booksAvailable;
            }
        }
        return null;
    }

}
