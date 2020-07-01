package services;

import entities.User;
import exceptions.BusinessException;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.UserFunctionalityManager;
import services.requests.RegisterUserRequest;
import services.responses.RegisterUserResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RegisterUserService implements  IService<RegisterUserRequest, RegisterUserResponse>  {

    private IUserFunctionalityManager userFunctionalityManager;

    public RegisterUserService(IUserFunctionalityManager userFunctionalityManager) {
        this.userFunctionalityManager = userFunctionalityManager;
    }

    public RegisterUserService(IUserRepository userRepository) {
        this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
    }


    @Override
    public void validate(RegisterUserRequest loginRequest) {
        //if (loginRequest.getUsername().isEmpty())
        throw new NotImplementedException();

        //if (loginRequest.getPassword().isEmpty())
        //throw new NotImplementedException();
    }

    @Override
    public RegisterUserResponse execute(RegisterUserRequest loginRequest) throws BusinessException {
        User user = userFunctionalityManager.register(loginRequest.getNewUser());
        return new RegisterUserResponse(user);
    }

    @Override
    public RegisterUserResponse rejectResponseBuilder(BusinessException businessException) {
        return new RegisterUserResponse(businessException);
    }
}
