package interfaces;

import entities.BookStock;

public interface IBookStockBuilder {

    BookStock build(int orderID);
}
