package interfaces.repository;

import entities.BooksInOrders;

public interface IBooksInOrdersRepository {

    BooksInOrders insert(BooksInOrders bookInOrder);

    BooksInOrders delete(int orderID);

    BooksInOrders fetch(int bookID);

    BooksInOrders fetchByOrderID(int orderID);
}
