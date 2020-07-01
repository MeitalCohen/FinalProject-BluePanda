package managers;

import interfaces.business.IAuthenticationValidator;
import interfaces.repository.IUserRepository;

public class AuthenticationValidator implements IAuthenticationValidator {

    private IUserRepository userRepository;

    public AuthenticationValidator(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public boolean IsUserExist(String userId)
    {
        return userRepository.fetch(userId) == null ? false : true;
    }
}
