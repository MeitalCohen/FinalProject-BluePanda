package initializer;

import interfaces.repository.IRepository;
import interfaces.repository.IUserRepository;
import services.IService;
import services.LoginService;
import services.requests.LoginRequest;
import services.requests.RequestBase;
import services.responses.LoginResponse;

import java.util.Hashtable;

public class ServicesIntializer {

    private Hashtable<String, IService> services;
    private RepositoriesInitializer repositoriesInitializer;
    public ServicesIntializer()
    {
        repositoriesInitializer = new RepositoriesInitializer();
        services = new Hashtable<>();

        services.put("LoginService",
                new LoginService((IUserRepository)repositoriesInitializer.getRepository("IUserRepository")));
    }

    public IService getService(RequestBase request) {
        if (services == null) return null;

        try {
            return services.get(request.getServiceName());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        //return null;    }
    }
}
