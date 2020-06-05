import entities.User;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.UserFunctionalityManager;

public class UserFunctionalityService {

    private IUserFunctionalityManager userFunctionalityManager;

    public UserFunctionalityService(IUserRepository userRepository)
    {
        this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
    }

    public User login(String username, String password)
    {
        return  this.userFunctionalityManager.login(username, password);
    }

    public boolean logout(String userID)
    {
        return  this.userFunctionalityManager.logout(userID);
    }

    public User signup (User user)
    {
        return this.userFunctionalityManager.signup(user);
    }

    public User update(User user) {
        return this.userFunctionalityManager.update(user);
    }


}
