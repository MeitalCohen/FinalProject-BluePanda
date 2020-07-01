package interfaces.repository;

import entities.Recommendation;

public interface IRecommendationRepository extends IRepository<Recommendation>{

    Recommendation update (Recommendation recommendation);

    Recommendation fetchRecommendationByUserIDBookID(String userID, int bookID);

    Recommendation fetchRecommendationByUserID(String userID);

    Recommendation fetchRecommendationByBookID(int bookID);
}
