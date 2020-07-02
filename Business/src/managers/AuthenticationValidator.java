package managers;

import exceptions.UserNotFoundException;
import interfaces.business.IAuthenticationValidator;
import interfaces.repository.IUserRepository;

public class AuthenticationValidator implements IAuthenticationValidator {

    private IUserRepository userRepository;

    public AuthenticationValidator(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void ValidateUserId(String userId) throws UserNotFoundException {
        if (userRepository.fetch(userId) == null)
            throw new UserNotFoundException();
    }
}
