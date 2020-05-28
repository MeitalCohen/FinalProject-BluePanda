import entities.BookStock;
import entities.BooksInOrders;
import entities.Order;
import entities.extension.BookExtension;
import interfaces.IBookStockBuilder;
import interfaces.repository.IBooksInOrdersRepository;
import interfaces.repository.IOrderRepository;

public class BookStockBuilder implements IBookStockBuilder {

    private IBooksInOrdersRepository _booksInOrderRepository;
    private IOrderRepository _orderRepository;

    public BookStockBuilder(IBooksInOrdersRepository booksInOrderRepository, IOrderRepository orderRepository)
    {
        _booksInOrderRepository = booksInOrderRepository;
        _orderRepository = orderRepository;
    }

    @Override
    public BookStock build(int orderID) {

        Order order = _orderRepository.fetch(orderID);
        if(order == null)
            return null;

        BooksInOrders bookInOrder = _booksInOrderRepository.fetchByOrderID(orderID);
        if (bookInOrder == null)
            return null;

        BookStock book = new BookStock(bookInOrder.getBookName(), order.getAuthorName(), order.getQuantity(),
                BookExtension.GenerateBookBarcode(),bookInOrder.getCategory(), BookExtension.GenerateBookCode());

        return book;
    }
}
