package interfaces.business;

import entities.User;
import exceptions.GeneralErrorException;
import exceptions.UserNotFoundException;

import java.util.Vector;

public interface IUserFunctionalityManager {

    User login(String username, String password) throws UserNotFoundException;

    boolean logout(String userID) throws GeneralErrorException;

    User register(User user);

    User update(User user);

    Vector<User> getAllUsers();
}
