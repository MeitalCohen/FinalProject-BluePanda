package tests;

//import org.testing.annotations.AfterTest;

import entities.User;
import exceptions.BusinessException;
import interfaces.repository.IUserRepository;
import managers.UserFunctionalityManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import repository.UserRepository;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserFunctionalityManagerTest {

    @InjectMocks
    UserFunctionalityManager userFunctionalityManager;
/*
    @Mock
    UserRepository userRepository;
    User newUser;

    @BeforeEach
    void setUp() {
        User newUser = new User("1", "username", "Meital", "Cohen", "password", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242");
        userRepository = mock(UserRepository.class);
        when(userRepository.fetch("username", "password")).thenReturn(newUser);
    }

    @AfterEach
    void tearDown() {
    }
*/
  /*  @Test
    void login() {
        IUserRepository userRepository = Mockito.mock(UserRepository.class);
        User newUser = new User("1", "username", "Meital", "Cohen", "password", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242");
        userRepository = mock(UserRepository.class);
        when(userRepository.fetch("username", "password")).thenReturn(newUser);

        userFunctionalityManager = new UserFunctionalityManager(userRepository);

        String username = "";
        String password ="";
        try {
            User existedUser = userFunctionalityManager.login(username, password);
            Assert.assertEquals(existedUser.getUserName(), newUser.getUserName());
            Assert.assertEquals(existedUser.getId(), newUser.getId());
        }
        catch (Exception e)
        {
            if (e instanceof BusinessException)
            {
                BusinessException bu = (BusinessException)e;
                Assert.fail(bu.getErrorMessage());
            }else
                Assert.fail(e.getMessage());
        }
    }
*/
    @Test
    void logout() {
    }

    @Test
    void register() {
    }

    @Test
    void update() {
    }

    @Test
    void getAllUsers() {
    }
}