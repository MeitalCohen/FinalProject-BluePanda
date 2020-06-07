package services;

import entities.User;
import exceptions.BusinessException;
import interfaces.business.IUserFunctionalityManager;
import interfaces.repository.IUserRepository;
import managers.UserFunctionalityManager;
import services.requests.LogoutRequest;
import services.responses.LogoutResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class LogoutService implements  IService<services.requests.LogoutRequest, services.responses.LogoutResponse> {

        private IUserFunctionalityManager userFunctionalityManager;

        public LogoutService(IUserFunctionalityManager userFunctionalityManager) {
                this.userFunctionalityManager = userFunctionalityManager;
        }

        public LogoutService(IUserRepository userRepository) {
                this.userFunctionalityManager = new UserFunctionalityManager(userRepository);
        }


        @Override
        public void validate(LogoutRequest loginRequest) {
                //if (loginRequest.getUsername().isEmpty())
                throw new NotImplementedException();

                //if (loginRequest.getPassword().isEmpty())
                //throw new NotImplementedException();
        }

        @Override
        public LogoutResponse execute(LogoutRequest loginRequest) throws BusinessException {
                boolean isSuccess = userFunctionalityManager.logout(loginRequest.getUserId());
                return new LogoutResponse();
        }

        @Override
        public LogoutResponse rejectResponseBuilder(BusinessException businessException) {
                return new LogoutResponse();
        }
}