package serviceHost;

import entities.BookStock;
import entities.Entity;
import entities.User;
import exceptions.BusinessException;
import initializer.RepositoriesInitializer;
import interfaces.repository.*;
import managers.BookBorrowManager;
import managers.BooksManager;

import java.util.Date;

public class PopulateDAta {
    private RepositoriesInitializer repositoriesInitializer;

    public PopulateDAta()
    {
        this.repositoriesInitializer = new RepositoriesInitializer();
    }

    public void InitData()
    {
        CreateUser();
        CreateBooks();
        //CreateBorrow();
    }
    public void CreateUser()
    {
        try {
            IRepository repository = repositoriesInitializer.getRepository("IUserRepository");
            User newUser = new User("316380013", "MeitalC", "Meital", "Cohen", "1234", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242");
            repository.insert(newUser);
            User newUser2 = new User("318688009", "LinZ", "Lin", "Zagron", "1234", 1, new Date(System.currentTimeMillis()), 1, "PROUD OF BAT YAM", "linw@fe.fd", "5555");
            repository.insert(newUser2);
        }
        catch (Exception e)
        {}
    }

    public void CreateBooks()
    {
        try {
            IRepository bookStockRepository = repositoriesInitializer.getRepository("IBookStockRepository");
            BookStock book = new BookStock("Meital", "Lin", 3, 3);
            bookStockRepository.insert(book);
        }
        catch (Exception e)
        {}
    }
    public void CreateBorrow()
    {
        IUserRepository userRep = (IUserRepository)repositoriesInitializer.getRepository("IUserRepository");
        IBorrowedBookRepository borrow = (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository");
        IBookStockRepository bookStockRepository = (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository");
        IConfigurationRepository config = (IConfigurationRepository)repositoriesInitializer.getRepository("IConfigurationRepository");

        BookBorrowManager borrowManager = new BookBorrowManager(userRep, borrow, bookStockRepository, config);
        BookStock book = bookStockRepository.fetch("Meital", "Lin");
        try {
            borrowManager.borrowBook("316380013", book);
        }
        catch (Exception e)
        {

        }
    }





}
