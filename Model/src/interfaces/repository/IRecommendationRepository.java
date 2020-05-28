package interfaces.repository;

import entities.Recommendation;

public interface IRecommendationRepository {

    public Recommendation insert(Recommendation recommendation);

    public Recommendation update (Recommendation recommendation);

    public Recommendation fetchRecommendationByUserIDBookID(int userID, int bookID);

    public Recommendation fetchRecommendationByUserID(int userID);

    public Recommendation fetchRecommendationByBookID(int bookID);
}
