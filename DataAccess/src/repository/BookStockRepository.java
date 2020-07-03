package repository;

import interfaces.repository.IBookStockRepository;
import entities.BookStock;

import java.util.Vector;
import java.util.stream.Collectors;

public class BookStockRepository extends RepositoryBase<BookStock> implements IBookStockRepository {
    private Vector<BookStock> books;

    public BookStockRepository()
    {
        this.books = this.loadData();
    }

    public BookStock insert(BookStock bookStock) {
        if (books == null)
            this.books = new Vector<>();

        BookStock bookResult = books.stream().filter(book -> book.getId() == bookStock.getId())
                .findFirst().orElse(null);

        if (bookResult != null)
            return null;

        boolean result = books.add(bookStock);
        if (result)
        {
            this.saveData(books);
            return bookStock;
        }
        else{
            return null;
        }
    }

    public BookStock update (BookStock bookStock) {
        if (books == null || books.isEmpty())
            return null;

        BookStock bookResult = books.stream().filter(book ->
                book.getId() == bookStock.getId()).findFirst().orElse(null);

        if (bookResult == null)
            return null;

        books.remove(bookResult);

        boolean result = books.add(bookStock);

        if (result) {
            this.saveData(books);
            return bookStock;
        } else {
            return null;
        }
    }

    public BookStock fetch(int bookID)
    {
        if (books == null || books.isEmpty())
            return null;

        return books.stream().filter(book->
                book.getId() == bookID).findFirst().orElse(null);
    }

    public BookStock fetchByCode(String bookCode)
    {
        if (books == null || books.isEmpty())
            return null;

        return books.stream().filter(book->
                book.getBookCode().equals(bookCode)).findFirst().orElse(null);
    }

    public Vector<BookStock> searchByName(String bookName)
    {
        if (books == null || books.isEmpty())
            return null;

        if (bookName.isEmpty() || bookName == null)
            return this.books;

        return books.stream().filter(book->book.getBookName().equals(bookName))
                .collect(Collectors.toCollection(() -> new Vector<BookStock>()));
    }

    public BookStock fetch(String bookName, String authorName)
    {
        if (bookName.isEmpty() || authorName.isEmpty())
            return null;

        return books.stream().filter(book -> book.getBookName().equals(bookName) && book.getAuthorName().equals(authorName))
                .findFirst().orElse(null);
    }
}
