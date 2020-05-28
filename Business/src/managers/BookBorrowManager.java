package managers;

import constants.ConfigurationsKeys;
import entities.*;
import entities.extension.DateExtension;
import entities.extension.UserExtension;
import enums.BookStatus;
import enums.BorrowStatus;
import enums.UserStatus;
import exceptions.*;
import interfaces.IBookAvailableStrategy;
import interfaces.IDebtCalculationStrategy;
import interfaces.repository.*;

import java.util.Date;
import java.util.Vector;

public class BookBorrowManager {

    private IUserRepository _userRepository;
    private IBorrowedBookRepository _borrowedBookRepository;
    private IBookStockRepository _bookStockRepository;
    private IConfigurationRepository _configurationRepository;
    private IDebtCalculationStrategy _debtCalculationStrategy;
    private IBookAvailableStrategy _bookAvailableStrategy;

    public BookBorrowManager(IUserRepository userRepository, IBorrowedBookRepository borrowedBookRepository,
                             IBookStockRepository bookStockRepository, IConfigurationRepository configurationRepository,
                             IDebtCalculationStrategy debtCalculationStrategy, IBookAvailableStrategy bookAvailableStrategy)
    {
        _userRepository = userRepository;
        _borrowedBookRepository = borrowedBookRepository;
        _bookStockRepository = bookStockRepository;
        _configurationRepository = configurationRepository;
        _debtCalculationStrategy = debtCalculationStrategy;
        _bookAvailableStrategy = bookAvailableStrategy;
    }

    public BorrowedBook extendBookBorrowing(User user, BookStock bookStock) throws BusinessException
    {
        if (user == null || bookStock == null)
            return null;

        BorrowedBook borrowedBook = _borrowedBookRepository.fetch(user.getId(), bookStock.getId());
        if (borrowedBook == null)
            throw new BorrowedBookNotFoundException();

        if (borrowedBook.isExtended())
            throw new ExtendBorrowingNotAllowException();

        if (borrowedBook.getFinalBorrowDate().before(new Date(System.currentTimeMillis())))
            throw new ExtendBorrowingNotAllowException();

        Configuration extendBorrowInDays = _configurationRepository.fetchConfigurationByName(ConfigurationsKeys.MaxDaysToBorrow);

        borrowedBook.setFinalBorrowDate(DateExtension.AddDaysToDate(borrowedBook.getFinalBorrowDate(), Integer.parseInt(extendBorrowInDays.getConfigValue())));
        borrowedBook.setExtended(true);

        return _borrowedBookRepository.update(borrowedBook);
    }

    public BorrowedBook borrowBook(User user, BookStock book) throws BusinessException
    {
        if (user == null || book == null)
            return null;

        boolean isBorrowAvailable = _bookAvailableStrategy.isBookAvailableToBorrow(book);
        if (!isBorrowAvailable)
            return null;

        //TODO: Check if user not maxed borrowed or doesn't have any debt
        Vector<BorrowedBook> usersBooks = _borrowedBookRepository.searchBorrowedBooksByUserID(user.getId(), BorrowStatus.Borrowed.StatusValue());
        Configuration maxBorrowAllow = _configurationRepository.fetchConfigurationByName(ConfigurationsKeys.MaxBooksBorrow);
        if (usersBooks == null || usersBooks.capacity() < Integer.parseInt(maxBorrowAllow.getConfigValue())) {
            //Allow to borrow
            //TODO: check if user has debt

            //String userID, int bookID, boolean isExtended, Date endBorrowRequest, Date endBorrowOfficial, int status
            BorrowedBook borrowRequest = new BorrowedBook(user.getId(), book.getId(), false, null, null, BorrowStatus.Borrowed.StatusValue());
            borrowRequest.setStartBorrowRequest(new Date(System.currentTimeMillis()));
            borrowRequest.setFinalBorrowDate(DateExtension.AddDaysToDate(new Date(System.currentTimeMillis()), 9));
            return _borrowedBookRepository.insert(borrowRequest);
        }
        else{
            throw new UserPassMaxBooksBorrowException();
        }
    }

    public BorrowedBook getBookBorrowInformation(User user, int bookID)
    {
        if (user == null)
            return null;

        return _borrowedBookRepository.fetch(user.getId(), bookID);
    }

    public BorrowedBook returnBook (User user, int borrowID) throws BusinessException
    {
        if (user == null)
            return null;

        BorrowedBook borrowedRequest = _borrowedBookRepository.fetch(borrowID);

        if (borrowedRequest == null)
            throw new BorrowedBookNotFoundException();

        Date today = new Date(System.currentTimeMillis());
        //TODO: if user is Manager or Librarian > doesn't need to wait for approval
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

        return _borrowedBookRepository.update(borrowedRequest);
    }

    public BorrowedBook approveBookReturn(User user, int borrowID) throws BusinessException
    {
        if (user == null)
            return null;

        if (UserExtension.isUserFitRole(user, UserStatus.Reader))
            throw new UserNotAuthorizeException();

        BorrowedBook borrow = _borrowedBookRepository.fetch(borrowID);
        if (borrow == null)
            throw new BorrowedBookNotFoundException();

        borrow.setEndBorrowOfficial(new Date(System.currentTimeMillis()));
        borrow.setStatus(BorrowStatus.Approved.StatusValue());

        return _borrowedBookRepository.update(borrow);
    }
}


