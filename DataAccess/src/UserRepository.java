import interfaces.repository.IUserRepository;
import entities.User;

import java.util.Vector;

public class UserRepository extends RepositoryBase<User> implements IUserRepository {

    private Vector<User> users;

    public UserRepository()
    {
        users = new Vector<User>(this.loadData());
    }

    public User insert(User user) {
        if (users == null || users.isEmpty())
            return null;

        User userResult = users.stream().filter(usr -> usr.getId() == user.getId())
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
                usr.getId() == user.getId()).findFirst().orElse(null);

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
                usr.getId() == userID).findFirst().orElse(null);
    }

    public User fetch(String username, String password)
    {
        if (username.isEmpty() || password.isEmpty())
            return  null;

        return users.stream().filter(usr->
                usr.getUserName() == username && usr.getPassword() == password)
                .findFirst().orElse(null);

    }

}
