package interfaces.business;

import entities.Recommendation;
import entities.User;

public interface IRecommendationManager {

    boolean addRecommendation(User user, Recommendation recommendation);
}
