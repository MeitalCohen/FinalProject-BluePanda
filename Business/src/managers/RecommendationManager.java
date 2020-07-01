package managers;

import entities.Recommendation;
import entities.User;
import interfaces.business.IRecommendationManager;
import interfaces.repository.IRecommendationRepository;

public class RecommendationManager implements IRecommendationManager {

    private IRecommendationRepository _recommendationRepository;

    public RecommendationManager(IRecommendationRepository recommendationRepository)
    {
        _recommendationRepository = recommendationRepository;
    }

    @Override
    public boolean addRecommendation(Recommendation recommendation) {

        Recommendation recommend = _recommendationRepository.insert(recommendation);

        return recommend == null ? false : true;
    }
}
