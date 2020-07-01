package interfaces.repository;

import entities.User;

import java.util.Date;
import java.util.Vector;

public interface IUserRepository extends IRepository<User>{

    //User insert(User user);

    User update(User user);

    User fetch(String userID);

    User fetch(String username, String password);

    Vector<User> searchUsers(Date startRange, Date endRange);

}
