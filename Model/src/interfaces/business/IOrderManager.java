package interfaces.business;

import entities.BooksInOrders;
import entities.Order;
import entities.User;
import exceptions.BusinessException;

public interface IOrderManager {

    Order cancelOrder(int orderID, String userID) throws BusinessException;

    Order insertOrder(User user, Order order, BooksInOrders bookInOrder) throws BusinessException;

    Order approveOrder(User user, Order order) throws BusinessException;
}
