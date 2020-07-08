package tests;

import entities.BookStock;
import entities.BorrowedBook;
import entities.User;
import entities.UserLending;
import enums.BooksFilter;
import managers.BooksManager;
import managers.UserBooksManager;
import org.junit.Assert;
import org.junit.Test;
import tests.repositories.BookStockRepositoryDummy;
import tests.repositories.BorrowedBookRepositoryDummy;
import tests.repositories.UserRepositoryDummy;

import java.util.Date;
import java.util.Vector;

public class BooksManagerTest {

    UserRepositoryDummy userRepositoryDummy;

    @Test
    public void getBooksByFilter_All_HappyPath() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Lin", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Lin", 3, "SiFi", "", "");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);
        BookStockRepositoryDummy bookStockRepositoryDummy = new BookStockRepositoryDummy();
        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);

        BorrowedBookRepositoryDummy borrowedBookRepositoryDummy = new BorrowedBookRepositoryDummy();
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);


        BooksManager booksManager = new BooksManager(bookStockRepositoryDummy, borrowedBookRepositoryDummy);
        try {
            Vector<BookStock> allBooks = booksManager.getBooksByFilter(BooksFilter.All, false);
            Assert.assertTrue(allBooks.size() == 2);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getBooksByFilter_Available_HappyPath() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Lin", 3, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Lin", 3, "SiFi", "", "");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "1", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);
        BookStockRepositoryDummy bookStockRepositoryDummy = new BookStockRepositoryDummy();
        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);

        BorrowedBookRepositoryDummy borrowedBookRepositoryDummy = new BorrowedBookRepositoryDummy();
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);


        BooksManager booksManager = new BooksManager(bookStockRepositoryDummy, borrowedBookRepositoryDummy);
        try {
            Vector<BookStock> allBooks = booksManager.getBooksByFilter(BooksFilter.AvailableOnly, false);
            Assert.assertTrue(allBooks.size() == 2);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getBooksByFilter_Available_NoElements() {
        Date now = new Date(System.currentTimeMillis());

        BookStock book = new BookStock("1","1", "Lin", 1, "SiFi", "", "");
        BookStock book2 = new BookStock("2","2", "Lin", 1, "SiFi", "", "");


        BorrowedBook borrowedBook1 = new BorrowedBook("1", "1", false, now, now, 1);
        BorrowedBook borrowedBook2 = new BorrowedBook("2", "2", false, now, now, 1);
        Vector<BorrowedBook> borrowedBooks = new Vector<>();
        borrowedBooks.add(borrowedBook1);
        borrowedBooks.add(borrowedBook2);

        Vector<BookStock> books = new Vector<>();
        books.add(book);
        books.add(book2);
        BookStockRepositoryDummy bookStockRepositoryDummy = new BookStockRepositoryDummy();
        bookStockRepositoryDummy.setup(TestState.ReturnObject, book, books);

        BorrowedBookRepositoryDummy borrowedBookRepositoryDummy = new BorrowedBookRepositoryDummy();
        borrowedBookRepositoryDummy.setup(TestState.ReturnObject, borrowedBook2, borrowedBooks);


        BooksManager booksManager = new BooksManager(bookStockRepositoryDummy, borrowedBookRepositoryDummy);
        try {
            Vector<BookStock> allBooks = booksManager.getBooksByFilter(BooksFilter.AvailableOnly, false);
            Assert.assertTrue(allBooks == null || allBooks.size() ==0);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
        }
    }



}
