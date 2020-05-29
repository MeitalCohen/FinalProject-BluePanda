package interfaces.repository;

import entities.Recommendation;

public interface IRecommendationRepository extends IRepository<Recommendation>{

    Recommendation update (Recommendation recommendation);

    Recommendation fetchRecommendationByUserIDBookID(int userID, int bookID);

    Recommendation fetchRecommendationByUserID(int userID);

    Recommendation fetchRecommendationByBookID(int bookID);
}
