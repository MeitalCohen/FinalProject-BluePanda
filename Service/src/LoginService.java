import entities.User;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.UserFunctionalityManager;
import services.requests.LoginRequest;
import services.responses.LoginResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LoginService implements  IService<services.requests.LoginRequest, services.responses.LoginResponse> {

    private IUserFunctionalityManager userFunctionalityManager;
    private User logedUser;

    public LoginService(IUserFunctionalityManager userFunctionalityManager)
    {
        this.userFunctionalityManager = userFunctionalityManager;
    }

    public LoginService(IUserRepository userRepository)
    {
        this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
    }


    @Override
    public void validate(LoginRequest loginRequest) {
        if (loginRequest.getUsername().isEmpty())
            throw new NotImplementedException();

        if (loginRequest.getPassword().isEmpty())
            throw new NotImplementedException();
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest) {
        logedUser = userFunctionalityManager.login(loginRequest.getUsername(), loginRequest.getPassword());
        return responseBuilder();
    }

    @Override
    public LoginResponse responseBuilder() {
        if (logedUser == null)
            return new LoginResponse();

        return null;
    }


}
