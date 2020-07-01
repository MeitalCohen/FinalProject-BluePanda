package managers;

import entities.User;
import interfaces.repository.IBookStockRepository;
import interfaces.repository.IBorrowedBookRepository;
import interfaces.repository.IUserRepository;

import java.util.Date;
import java.util.Map;
import java.util.Vector;

public class UserReportManager {

    private IUserRepository userRepository;
    private IBookStockRepository bookStockRepository;
    private IBorrowedBookRepository borrowedBookRepository;

    public UserReportManager(IUserRepository userRepository, IBookStockRepository bookStockRepository,
                             IBorrowedBookRepository borrowedBookRepository)
    {
        this.userRepository = userRepository;
        this.bookStockRepository = bookStockRepository;
        this.borrowedBookRepository = borrowedBookRepository;
    }

    public Vector<User> getRegisteredUserByTimeRange(Date startRange, Date endRange)
    {
        return this.userRepository.searchUsers(startRange, endRange);
    }

    public void getStatics()
    {

    }

    public void getNumberOfBooksByUser()
    {

    }

    public void getFavoriteUserCategory()
    {

    }
}
