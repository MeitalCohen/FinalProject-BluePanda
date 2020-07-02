package managers;

import constants.ConfigurationsKeys;
import entities.*;
import entities.extension.DateExtension;
import entities.extension.UserExtension;
import enums.BorrowStatus;
import enums.UserStatus;
import exceptions.*;
import interfaces.IBookAvailableStrategy;
import interfaces.business.IBookBorrowManager;
import interfaces.repository.*;
import strategy.BookAvailableStrategy;

import java.util.Date;
import java.util.Vector;

public class BookBorrowManager implements IBookBorrowManager {

    private IUserRepository userRepository;
    private IBorrowedBookRepository borrowedBookRepository;
    private IBookStockRepository bookStockRepository;
    private IConfigurationRepository configurationRepository;
    private IBookAvailableStrategy bookAvailableStrategy;

    public BookBorrowManager(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository,
                             IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository)
    {
        this.userRepository = userRepository;
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookStockRepository = bookStockRepository;
        this.configurationRepository = configurationRepository;
        this.bookAvailableStrategy = new BookAvailableStrategy(borrowedBookRepository);
    }

    public BorrowedBook extendBookBorrowing(String userId, BookStock bookStock) throws BusinessException
    {
        User user = this.userRepository.fetch(userId);
        if (user == null || bookStock == null)
            return null;

        BorrowedBook borrowedBook = borrowedBookRepository.fetch(user.getId(), bookStock.getId());
        if (borrowedBook == null)
            throw new BorrowedBookNotFoundException();

        if (borrowedBook.isExtended())
            throw new ExtendBorrowingNotAllowException();

        if (borrowedBook.getFinalBorrowDate().before(new Date(System.currentTimeMillis())))
            throw new ExtendBorrowingNotAllowException();

        Configuration extendBorrowInDays = configurationRepository.fetchConfigurationByName(ConfigurationsKeys.MaxDaysToBorrow);

        borrowedBook.setFinalBorrowDate(DateExtension.AddDaysToDate(borrowedBook.getFinalBorrowDate(), Integer.parseInt(extendBorrowInDays.getConfigValue())));
        borrowedBook.setExtended(true);

        return borrowedBookRepository.update(borrowedBook);
    }

    public BorrowedBook borrowBook(String userId, BookStock book) throws BusinessException
    {
        User user = this.userRepository.fetch(userId);
        if (user == null || book == null)
            return null;

        boolean isBorrowAvailable = bookAvailableStrategy.isBookAvailableToBorrow(book);
        if (!isBorrowAvailable)
            return null;

        Vector<BorrowedBook> usersBooks = borrowedBookRepository.searchBorrowedBooksByUserID(user.getId(), BorrowStatus.Borrowed.StatusValue());
        Configuration maxBorrowAllow = configurationRepository.fetchConfigurationByName(ConfigurationsKeys.MaxBooksBorrow);
        if (usersBooks == null || usersBooks.capacity() < Integer.parseInt(maxBorrowAllow.getConfigValue())) {
            //Allow to borrow

            BorrowedBook borrowRequest = new BorrowedBook(user.getId(), book.getId(), false, null, null, BorrowStatus.Borrowed.StatusValue());
            borrowRequest.setStartBorrowRequest(new Date(System.currentTimeMillis()));
            borrowRequest.setFinalBorrowDate(DateExtension.AddDaysToDate(new Date(System.currentTimeMillis()), 9));
            return borrowedBookRepository.insert(borrowRequest);
        }
        else{
            throw new UserPassMaxBooksBorrowException();
        }
    }

    public BorrowedBook getBookBorrowInformation(String userId, int bookID)
    {
        User user = this.userRepository.fetch(userId);
        if (user == null)
            return null;

        return borrowedBookRepository.fetch(user.getId(), bookID);
    }

    public BorrowedBook returnBook (String userId, int borrowID) throws BusinessException
    {
        User user = this.userRepository.fetch(userId);
        if (user == null)
            return null;

        BorrowedBook borrowedRequest = borrowedBookRepository.fetch(borrowID);

        if (borrowedRequest == null)
            throw new BorrowedBookNotFoundException();

        Date today = new Date(System.currentTimeMillis());
        //NOTE: if user is Manager or Librarian -> doesn't need to wait for approval
        if (!UserExtension.isUserFitRole(user, UserStatus.Reader))
        {
            borrowedRequest.setEndBorrowOfficial(today);
            borrowedRequest.setEndBorrowRequest(today);
            borrowedRequest.setStatus(BorrowStatus.Approved.StatusValue());
        }
        else{
            borrowedRequest.setEndBorrowRequest(today);
            borrowedRequest.setStatus(BorrowStatus.WaitingForReturnApproval.StatusValue());
        }

        return borrowedBookRepository.update(borrowedRequest);
    }

    public BorrowedBook approveBookReturn(String userId, int borrowID) throws BusinessException
    {
        User user = this.userRepository.fetch(userId);
        if (user == null)
            return null;

        if (UserExtension.isUserFitRole(user, UserStatus.Reader))
            throw new UserNotAuthorizeException();

        BorrowedBook borrow = borrowedBookRepository.fetch(borrowID);
        if (borrow == null)
            throw new BorrowedBookNotFoundException();

        borrow.setEndBorrowOfficial(new Date(System.currentTimeMillis()));
        borrow.setStatus(BorrowStatus.Approved.StatusValue());

        return borrowedBookRepository.update(borrow);
    }
}


