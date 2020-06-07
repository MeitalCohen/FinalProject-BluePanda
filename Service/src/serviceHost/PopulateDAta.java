package serviceHost;

import entities.User;
import initializer.RepositoriesInitializer;
import interfaces.repository.IRepository;

import java.util.Date;

public class PopulateDAta {
    private RepositoriesInitializer repositoriesInitializer;

    public PopulateDAta()
    {
        this.repositoriesInitializer = new RepositoriesInitializer();
    }

    public void CreateUser()
    {
        IRepository repository = repositoriesInitializer.getRepository("IUserRepository");
        User newUser = new User("316380013", "MeitalC", "Meital", "Cohen", "1234", 3, new Date(System.currentTimeMillis()), 1, "Tel Aviv", "rew@fe.fd", "43242");
        repository.insert(newUser);
    }


}
