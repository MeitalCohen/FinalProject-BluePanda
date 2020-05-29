package interfaces.repository;

import entities.User;

public interface IUserRepository extends IRepository<User>{

    //User insert(User user);

    User update(User user);

    User fetch(String userID);

    User fetch(String username, String password);

}
