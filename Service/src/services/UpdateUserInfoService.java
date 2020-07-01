package services;

import entities.User;
import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IUserFunctionalityManager;
import managers.AuthenticationValidator;
import services.requests.UpdateUserInfoRequest;
import services.responses.UpdateUserInfoResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UpdateUserInfoService implements IService<UpdateUserInfoRequest, UpdateUserInfoResponse> {

    private IUserFunctionalityManager userFunctionalityManager;
    private IAuthenticationValidator authenticationValidator;

    public UpdateUserInfoService(IUserFunctionalityManager userFunctionalityManager, IAuthenticationValidator authenticationValidator)
    {
        this.userFunctionalityManager = userFunctionalityManager;
        this.authenticationValidator = authenticationValidator;
    }

    @Override
    public void validate(UpdateUserInfoRequest updateUserInfoRequest) {

        this.authenticationValidator.IsUserExist(updateUserInfoRequest.getUserToUpdate().getId());

        throw new NotImplementedException();
    }

    @Override
    public UpdateUserInfoResponse execute(UpdateUserInfoRequest updateUserInfoRequest) throws BusinessException {
        User updatedUser = this.userFunctionalityManager.update(updateUserInfoRequest.getUserToUpdate());
        return new UpdateUserInfoResponse(updatedUser);
    }

    @Override
    public UpdateUserInfoResponse rejectResponseBuilder(BusinessException businessException) {
        return new UpdateUserInfoResponse(businessException);
    }
}
