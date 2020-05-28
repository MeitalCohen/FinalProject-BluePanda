package interfaces.repository;

import entities.User;

public interface IUserRepository{

    User insert(User user);

    User update(User user);

    User fetch(String userID);

    User fetch(String username, String password);

}
