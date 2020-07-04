package repository;

import entities.Order;
import entities.extension.DateExtension;
import interfaces.repository.IUserRepository;
import entities.User;

import java.util.Date;
import java.util.Vector;
import java.util.stream.Collectors;

public class UserRepository extends RepositoryBase<User> implements IUserRepository {

    private Vector<User> users;

    public UserRepository()
    {
        users = this.loadData();
    }

    public User insert(User user) {
        if (users == null)
            this.users = new Vector<>();

        User userResult = users.stream().filter(usr -> usr.getId().equals(user.getId()))
                .findFirst().orElse(null);

        if (userResult != null)
            return null;

        boolean result = users.add(user);
        if (result)
        {
            this.saveData(users);
            return user;
        }
        else{
            return null;
        }
    }

    public User update (User user) {
        if (users == null || users.isEmpty())
            return null;

        User userResult = users.stream().filter(usr ->
                usr.getId().equals(user.getId())).findFirst().orElse(null);

        if (userResult == null)
            return null;

        users.remove(userResult);

        boolean result = users.add(user);

        if (result) {
            this.saveData(users);
            return user;
        } else {
            return null;
        }
    }

    public User fetch(String userID)
    {
        if (users == null || users.isEmpty())
            return null;

        return users.stream().filter(usr->
                usr.getId().equals(userID)).findFirst().orElse(null);
    }

    public User fetch(String username, String password)
    {
        if (username.isEmpty() || password.isEmpty())
            return  null;

        return users.stream().filter(usr->
                usr.getUserName().equals(username) && usr.getPassword().equals(password))
                .findFirst().orElse(null);

    }

    public Vector<User> getAllUsers()
    {
        return users;
    }

}
