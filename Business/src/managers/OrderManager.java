package managers;

import builder.BookStockBuilder;
import entities.*;
import entities.extension.UserExtension;
import enums.UserStatus;
import exceptions.*;
import interfaces.IBookStockBuilder;
import interfaces.ITotalOrdersCalculationStrategy;
import interfaces.repository.IBooksInOrdersRepository;
import interfaces.business.IOrderManager;
import interfaces.repository.*;
import strategy.TotalOrdersCalculationStrategy;

import java.util.Date;

public class OrderManager implements IOrderManager {

    private IOrderRepository _orderRepository;
    private IUserRepository _userRepository;
    private IBookStockRepository bookStockRepository;
    private ITotalOrdersCalculationStrategy totalOrdersCalculationStrategy;
    private IBooksInOrdersRepository _booksInOrdersRepository;
    private IBookStockBuilder bookStockBuilder;

    public OrderManager(IOrderRepository orderRepository, IUserRepository userRepository,
                        IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository,
                        IBooksInOrdersRepository booksInOrdersRepository)
    {
        _orderRepository = orderRepository;
        _userRepository = userRepository;
        this.bookStockRepository = bookStockRepository;
        totalOrdersCalculationStrategy = new TotalOrdersCalculationStrategy(configurationRepository, orderRepository);
        _booksInOrdersRepository = booksInOrdersRepository;
        bookStockBuilder = new BookStockBuilder(bookStockRepository, booksInOrdersRepository);
    }


    @Override
    public Order cancelOrder(String orderID, String userID) throws BusinessException {
        User user = _userRepository.fetch(userID);

        if (user == null)
            throw new UserNotFoundException();

        if (!UserExtension.isUserFitRole(user, UserStatus.Manager))
            throw new UserNotAuthorizeException();

        Order order = _orderRepository.fetch(orderID);

        if(order == null)
            throw new OrderNotFoundException();

        order.setCanceled(true);
        order.setOrderCheckedDate(new Date(System.currentTimeMillis()));

        return _orderRepository.update(order);
    }

    @Override
    public Order insertOrder(String userId, Order order, BooksInOrders bookInOrder) throws BusinessException {
        if (bookInOrder == null)
            throw new InvalidBookException();

        User userTemp = _userRepository.fetch(userId);

        if (userTemp == null)
            throw new UserNotFoundException();

        if (UserExtension.isUserFitRole(userTemp, UserStatus.Reader))
            throw new UserNotAuthorizeException();

        Order newOrder = _orderRepository.insert(order);
        if (newOrder != null)
        {
            bookInOrder.setOrderID(newOrder.getOrderID());
            BooksInOrders bookTemp = _booksInOrdersRepository.insert(bookInOrder);
            if (bookTemp != null) {
                if (UserExtension.isUserFitRole(userTemp, UserStatus.Manager))
                {
                    approveOrder(userId, order);
                }
                return newOrder;
            }
            else{
                _orderRepository.delete(newOrder);
                throw new GeneralErrorException();
            }
        }
        return  null;
    }

    @Override
    public Order approveOrder(String userId, Order order)throws BusinessException {
        User userTemp = _userRepository.fetch(userId);

        if (userTemp == null)
            throw new UserNotFoundException();

        if (!UserExtension.isUserFitRole(userTemp, UserStatus.Manager))
            throw new UserNotAuthorizeException();

        if (order.getPrice() > 0) {

            float updatedOrdersBudget = totalOrdersCalculationStrategy.calculate();

            if (updatedOrdersBudget >= order.getPrice()) {
                //TODO:insert new book/update quantity
                BookStock book = bookStockRepository.fetch(order.getBookName(), order.getAuthorName());
                if (book == null) {
                    //Insert New Book
                    BookStock newBook = bookStockBuilder.build(order);

                    if (newBook == null)
                        throw new GeneralErrorException();

                    bookStockRepository.insert(newBook);
                } else {
                    //Update Quantity
                    book.setQuantity(book.getQuantity() + order.getQuantity());
                    bookStockRepository.update(book);
                }
            }
            else{
                throw new OrderPriceHigherThanBudgetException();
            }
        }
        else{
            BookStock book = bookStockRepository.fetch(order.getBookName(), order.getAuthorName());
            if (book == null) {
                //Insert New Book
                BookStock newBook = bookStockBuilder.build(order);

                if (newBook == null)
                    throw new GeneralErrorException();

                bookStockRepository.insert(newBook);
            } else {
                //Update Quantity
                book.setQuantity(book.getQuantity() + order.getQuantity());
                bookStockRepository.update(book);
            }
        }
            _booksInOrdersRepository.delete(order.getOrderID());

            order.setOrderCheckedDate(new Date(System.currentTimeMillis()));
            order.setCanceled(false);
            _orderRepository.update(order);

        return order;
    }

}
