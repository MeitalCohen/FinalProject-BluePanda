package services.responses;

import entities.User;
import exceptions.BusinessException;
import services.requests.RegisterUserRequest;

public class RegisterUserResponse extends ResponseBase  {
    private User updatedUser;

    public RegisterUserResponse(User user)
    {
        this.buildResponse();
        this.updatedUser = user;
    }

    public RegisterUserResponse(BusinessException exception)
    {
        this.rejectResponse(exception);
        this.updatedUser = null;
    }

    public User getUser()
    {
        return updatedUser;
    }

}
