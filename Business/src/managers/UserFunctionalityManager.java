package managers;

import entities.User;
import exceptions.GeneralErrorException;
import exceptions.UserNotFoundException;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;

import java.util.Vector;

public class UserFunctionalityManager implements IUserFunctionalityManager {

    private IUserRepository userRepository;

    public UserFunctionalityManager(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) throws UserNotFoundException {
        User user = userRepository.fetch(username, password);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }

    public boolean logout(String userID) throws GeneralErrorException {
        if (userRepository.fetch(userID) != null)
            return true;
        throw new GeneralErrorException();
    }

    public User register(User user)
    {
        return userRepository.insert(user);
    }

    public User update(User user)
    {
        User oldUser = userRepository.fetch(user.getId());
        if (oldUser == null)
             return null;
        return userRepository.update(user);
    }

    public Vector<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }
}
