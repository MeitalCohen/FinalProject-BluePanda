package interfaces.business;

import entities.BookStock;
import enums.BooksFilter;

import java.util.List;

public interface IBooksManager {
    List<BookStock> getBooksByFilter(BooksFilter filter);
}
