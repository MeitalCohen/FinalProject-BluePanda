package repositoriesDummy;

import entities.BookStock;
import exceptions.BusinessException;
import interfaces.repository.IBookStockRepository;

import java.util.Vector;

public class BookStockRepositoryDummy implements IBookStockRepository {
    @Override
    public BookStock update(BookStock bookStock) {
        return null;
    }

    @Override
    public BookStock fetch(String bookID) {
        return null;
    }

    @Override
    public BookStock fetchByCode(String bookCode) {
        return null;
    }

    @Override
    public Vector<BookStock> searchByName(String bookName) {
        return null;
    }

    @Override
    public BookStock fetch(String bookName, String authorName) {
        return null;
    }

    @Override
    public BookStock delete(BookStock bookStock, int quantity) throws BusinessException {
        return null;
    }

    @Override
    public BookStock insert(BookStock bookStock) throws BusinessException {
        return null;
    }
}
