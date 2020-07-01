package services;

import exceptions.BusinessException;
import interfaces.business.IAuthenticationValidator;
import interfaces.business.IRecommendationManager;
import services.requests.RecommendRequest;
import services.responses.RecommendResponse;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class RecommendService implements IService<RecommendRequest, RecommendResponse> {

    private IRecommendationManager recommendationManager;
    private IAuthenticationValidator authenticationValidator;

    public RecommendService(IRecommendationManager recommendationManager, IAuthenticationValidator authenticationValidator)
    {
        this.recommendationManager = recommendationManager;
        this.authenticationValidator = authenticationValidator;
    }

    @Override
    public void validate(RecommendRequest recommendRequest) {
        //TODO: throw exception
        this.authenticationValidator.IsUserExist(recommendRequest.getRecommendation().getUserID());

        //if (loginRequest.getUsername().isEmpty())
        throw new NotImplementedException();

        //if (loginRequest.getPassword().isEmpty())
        //throw new NotImplementedException();
    }

    @Override
    public RecommendResponse execute(RecommendRequest recommendRequest) throws BusinessException {
        boolean result = recommendationManager.addRecommendation(recommendRequest.getRecommendation());
        return new RecommendResponse(result);
    }

    @Override
    public RecommendResponse rejectResponseBuilder(BusinessException businessException) {
        return new RecommendResponse(businessException);
    }

}
