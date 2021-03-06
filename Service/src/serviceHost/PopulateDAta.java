package serviceHost;

import constants.ConfigurationsKeys;
import entities.*;
import exceptions.BusinessException;
import initializer.RepositoriesInitializer;
import interfaces.repository.*;
import managers.BookBorrowManager;
import managers.BooksManager;

import java.util.Date;
import java.util.Vector;

public class PopulateDAta {
    private RepositoriesInitializer repositoriesInitializer;

    public PopulateDAta()
    {
        this.repositoriesInitializer = new RepositoriesInitializer();
    }

    public void InitData()
    {
        InitConfiguration();
        CreateUser();
        CreateBooks();
        //CreateBorrowForReader();
        //CreateBorrow();
        CreateEvents();
    }

    public void InitConfiguration()
    {
        IRepository repository = repositoriesInitializer.getRepository("IConfigurationRepository");

        Configuration configOrderBudgets = new Configuration(ConfigurationsKeys.OrdersBudget, "10000", "Orders Budget");
        Configuration configMaxBorrow = new Configuration(ConfigurationsKeys.MaxBooksBorrow, "5", "Max Books User Can Borrow");
        Configuration configMaxDaysBorrow = new Configuration(ConfigurationsKeys.MaxDaysToBorrow, "10000", "Max Days to Borrow");
        try {
            repository.insert(configOrderBudgets);
        } catch (Exception e){}
        try {
            repository.insert(configMaxBorrow);
        }catch (Exception ex){}
        try {
            repository.insert(configMaxDaysBorrow);
        }catch (Exception exce){}
    }

    public void CreateEvents()
    {
        IRepository repository = repositoriesInitializer.getRepository("IEventRepository");

        Vector<Event> events = new Vector<Event>();
        events.add(new Event("1", "Disquisition", "6347474345245", new Date(System.currentTimeMillis()), "J.K. Rowling", false));
        events.add(new Event("2","Monologue", "63474444345245", new Date(System.currentTimeMillis()), "Shakespeare", true));
        try {
            for (Event e: events) {
                repository.insert(e);
            }
        }catch (Exception exce){}
    }

    public void CreateUser()
    {
        try {
             IUserRepository repository = (IUserRepository)repositoriesInitializer.getRepository("IUserRepository");
             try {
                 User newUser = new User("316380013", "MeitalC", "Meital", "Cohen", "1234", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
                 repository.insert(newUser);
             }catch (Exception e){}
            try {
                User newUser = new User("12135342", "Lior", "Lior", "Lior", "123", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
                repository.insert(newUser);
            }catch (Exception e){}
            try {
                User newUser = new User("22344444", "LiorReader", "Lior", "Lior", "123", 1, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242", true);
                repository.insert(newUser);
            }catch (Exception e){}
            try {
            User newUser2 = new User("318688009", "LinZ", "Lin", "Zagron", "1234", 3, new Date(System.currentTimeMillis()), 1, "PROUD OF BAT YAM", "linw@fe.fd", "5555", true);
            repository.insert(newUser2);
            }catch (Exception e){}

        }
        catch (Exception e)
        {}
    }

    public void CreateBorrowForReader()
    {
        IUserRepository userRep = (IUserRepository)repositoriesInitializer.getRepository("IUserRepository");
        IBorrowedBookRepository borrow = (IBorrowedBookRepository)repositoriesInitializer.getRepository("IBorrowedBookRepository");
        IBookStockRepository bookStockRepository = (IBookStockRepository)repositoriesInitializer.getRepository("IBookStockRepository");
        IConfigurationRepository config = (IConfigurationRepository)repositoriesInitializer.getRepository("IConfigurationRepository");

        BookBorrowManager borrowManager = new BookBorrowManager(userRep, borrow, bookStockRepository, config);
        BookStock book = bookStockRepository.fetch("book1", "Lin");
        try {
            borrowManager.borrowBook("318688009", book.getId());
        }
        catch (Exception e)
        {

        }
    }


    public void CreateBooks()
    {
        IRepository bookStockRepository = repositoriesInitializer.getRepository("IBookStockRepository");

        try {
            BookStock book = new BookStock("Time Travel", "Ef-Shari", 15, "Science Fiction");
            bookStockRepository.insert(book);
        }
        catch(Exception ex){}
        try{
            BookStock book1 = new BookStock("Vogue", "Elinor", 13, "Magazine");
            bookStockRepository.insert(book1);
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
            borrowManager.borrowBook("316380013", book.getId());
        }
        catch (Exception e)
        {

        }
    }
}
