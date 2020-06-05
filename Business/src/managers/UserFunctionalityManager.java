package managers;

import entities.User;
import exceptions.GeneralErrorException;
import exceptions.UserNotFoundException;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;

public class UserFunctionalityManager implements IUserFunctionalityManager {

    private IUserRepository _userRepository;

    public UserFunctionalityManager(IUserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    public User login(String username, String password) throws UserNotFoundException {
        User user = _userRepository.fetch(username, password);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }

    public boolean logout(String userID) throws GeneralErrorException {
        if (_userRepository.fetch(userID) != null)
            return true;
        throw new GeneralErrorException();
    }

    public User signup (User user)
    {
        return _userRepository.insert(user);
    }

    public User update(User user)
    {
        User oldUser = _userRepository.fetch(user.getId());
        if (oldUser == null)
             return null;
        return _userRepository.update(user);
    }
}
