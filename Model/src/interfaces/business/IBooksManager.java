package interfaces.business;

import entities.BookStock;
import enums.BooksFilter;
import exceptions.BusinessException;

import java.util.List;
import java.util.Vector;

public interface IBooksManager {
    Vector<BookStock> getBooksByFilter(BooksFilter filter);
    BookStock removeBookQuantity(String bookId, int quantity) throws BusinessException;

}
