package interfaces.business;

import exceptions.UserNotFoundException;

public interface IAuthenticationValidator {

    void ValidateUserId(String userId) throws UserNotFoundException;
}
