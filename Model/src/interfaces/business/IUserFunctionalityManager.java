package interfaces.business;

import entities.User;
import exceptions.GeneralErrorException;
import exceptions.UserNotFoundException;

public interface IUserFunctionalityManager {

    User login(String username, String password) throws UserNotFoundException;

    boolean logout(String userID) throws GeneralErrorException;

    User signup (User user);

    User update(User user);
}
