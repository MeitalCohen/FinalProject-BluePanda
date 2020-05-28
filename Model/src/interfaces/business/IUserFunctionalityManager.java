package interfaces.business;

import entities.User;

public interface IUserFunctionalityManager {

    User login(String username, String password);

    boolean logout(String userID);

    User signup (User user);

    User update(User user);
}
