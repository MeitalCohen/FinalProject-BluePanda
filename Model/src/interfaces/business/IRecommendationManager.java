package interfaces.business;

import entities.Recommendation;
import entities.User;

import java.util.Vector;

public interface IRecommendationManager {

    boolean addRecommendation(Recommendation recommendation);
     Vector<Recommendation> getAllRecommendationByBookId(String bookId);

}
