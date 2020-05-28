package managers;

import entities.User;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;

public class UserFunctionalityManager implements IUserFunctionalityManager {

    private IUserRepository _userRepository;

    public UserFunctionalityManager(IUserRepository userRepository)
    {
        _userRepository = userRepository;
    }

    public User login(String username, String password)
    {
        return  _userRepository.fetch(username, password);
    }

    public boolean logout(String userID)
    {
        return  _userRepository.fetch(userID) == null ? false : true;
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
