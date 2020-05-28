package interfaces.repository;

import entities.Order;

import java.util.Date;
import java.util.Vector;

public interface IOrderRepository {

    public Order insert(Order order);

    public Order delete(Order order);

    public Order fetch(int orderID);

    public Order update(Order order);

    public Vector<Order> searchOrders(Date startRange, Date endRage);
}
