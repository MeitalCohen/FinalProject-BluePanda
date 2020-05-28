import interfaces.business.IUserFunctionalityManager;

public class UserFunctionalityService {

    private IUserFunctionalityManager userFunctionalityManager;

    public UserFunctionalityService(IUserFunctionalityManager userFunctionalityManager)
    {
        this.userFunctionalityManager = userFunctionalityManager;
    }


}
