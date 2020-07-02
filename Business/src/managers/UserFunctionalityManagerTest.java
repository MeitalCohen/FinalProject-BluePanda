package managers;

import interfaces.repository.IUserRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.jupiter.api.Assertions.*;

public class UserFunctionalityManagerTest {

    @Mock
    private IUserRepository userRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    private UserFunctionalityManager userFunctionalityManager = new UserFunctionalityManager(userRepository);
    @Test
    void login() {

    }

    @Test
    void logout() {
    }

    @Test
    void register() {
    }

    @Test
    void update() {
    }
}