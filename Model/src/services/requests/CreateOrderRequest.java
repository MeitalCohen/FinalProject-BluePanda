package services.requests;

import entities.BooksInOrders;
import entities.Order;

public class CreateOrderRequest extends RequestBase{

    private String userId;
    private Order newOrder;
    private BooksInOrders booksInOrder;

    public CreateOrderRequest(String userId, Order newOrder, BooksInOrders booksInOrder) {
        this.userId = userId;
        this.newOrder = newOrder;
        this.booksInOrder = booksInOrder;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Order getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Order newOrder) {
        this.newOrder = newOrder;
    }

    public BooksInOrders getBooksInOrder() {
        return booksInOrder;
    }

    public void setBooksInOrder(BooksInOrders booksInOrder) {
        this.booksInOrder = booksInOrder;
    }
}
