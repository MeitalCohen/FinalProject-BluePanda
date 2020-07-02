package builder;

import entities.BookStock;
import entities.BooksInOrders;
import interfaces.IBookStockBuilder;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBooksInOrdersRepository;

public class BookStockBuilder implements IBookStockBuilder {

    private IBookStockRepository bookStockRepository;
    private IBooksInOrdersRepository booksInOrdersRepository;

    public BookStockBuilder(IBookStockRepository bookStockRepository, IBooksInOrdersRepository booksInOrdersRepository)
    {
        this.bookStockRepository = bookStockRepository;
        this.booksInOrdersRepository = booksInOrdersRepository;
    }

    public BookStock build(int orderId)
    {
        BooksInOrders bookInOrder = booksInOrdersRepository.fetchByOrderID(orderId);
        if (bookInOrder == null) return null;

        return bookStockRepository.fetch(bookInOrder.getBookInOrderID());
    }
}
