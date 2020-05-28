import entities.BooksInOrders;
import interfaces.repository.IBooksInOrdersRepository;

import java.util.Vector;

public class BooksInOrdersRepository extends RepositoryBase<BooksInOrders> implements IBooksInOrdersRepository {

    private Vector<BooksInOrders> booksInOrder;

    public BooksInOrdersRepository()
    {
        this.booksInOrder = new Vector<>(this.loadData());
    }

    public BooksInOrders insert(BooksInOrders bookInOrder) {
        if (booksInOrder == null || booksInOrder.isEmpty())
            return null;

        BooksInOrders bookResult = booksInOrder.stream().filter(book -> book.getOrderID() == bookInOrder.getOrderID() &&
                                                                book.getBookInOrderID() == bookInOrder.getBookInOrderID())
                                                        .findFirst().orElse(null);

        if (bookResult != null)
            return null;

        boolean result = booksInOrder.add(bookInOrder);
        if (result)
        {
            this.saveData(booksInOrder);
            return bookInOrder;
        }
        else{
            return null;
        }
    }

    public BooksInOrders delete(int orderID)
    {
        if (booksInOrder == null || booksInOrder.isEmpty())
            return null;

        BooksInOrders bookInOrder = booksInOrder.stream().filter(book->
                book.getOrderID() == orderID).findFirst().orElse(null);

        if (bookInOrder != null) {
            boolean result = booksInOrder.remove(bookInOrder);
            if (result)
                return bookInOrder;
        }

        return  null;
    }

    public BooksInOrders fetch(int bookID)
    {
        if (booksInOrder == null || booksInOrder.isEmpty())
            return null;

        return booksInOrder.stream().filter(book->
                book.getBookInOrderID() == bookID).findFirst().orElse(null);
    }

    public BooksInOrders fetchByOrderID(int orderID)
    {
        if (booksInOrder == null || booksInOrder.isEmpty())
            return null;

        return booksInOrder.stream().filter(book->
                book.getBookInOrderID() == orderID).findFirst().orElse(null);
    }


}
