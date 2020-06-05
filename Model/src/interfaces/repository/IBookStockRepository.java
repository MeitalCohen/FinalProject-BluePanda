package interfaces.repository;

import entities.BookStock;

import java.util.Vector;

public interface IBookStockRepository extends IRepository<BookStock>{

    BookStock update (BookStock bookStock);

    BookStock fetch(int bookID);

    BookStock fetchByCode(String bookCode);

    Vector<BookStock> searchByName(String bookName);

    BookStock fetch(String bookName, String authorName);
}