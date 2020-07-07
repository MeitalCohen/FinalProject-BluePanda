package interfaces.business;

import entities.BooksInOrders;
import entities.Order;
import entities.User;
import exceptions.BusinessException;

public interface IOrderManager {

    //Order cancelOrder(String orderID, String userID) throws BusinessException;

    Order insertOrder(String userId, Order order, BooksInOrders bookInOrder) throws BusinessException;

    //Order approveOrder(String userId, Order order) throws BusinessException;
}
