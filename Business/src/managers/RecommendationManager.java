package managers;

import entities.Recommendation;
import interfaces.business.IRecommendationManager;
import interfaces.repository.IRecommendationRepository;

import java.util.Vector;

public class RecommendationManager implements IRecommendationManager {

    private IRecommendationRepository recommendationRepository;

    public RecommendationManager(IRecommendationRepository recommendationRepository)
    {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public boolean addRecommendation(Recommendation recommendation) {

        Recommendation recommend = recommendationRepository.insert(recommendation);

        return recommend == null ? false : true;
    }

    public Vector<Recommendation> getAllRecommendationByBookId(int bookId)
    {
        return this.recommendationRepository.searchRecommendationByBookID(bookId);
    }
}
