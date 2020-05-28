import interfaces.repository.IRecommendationRepository;
import entities.Recommendation;

import java.util.Vector;

public class RecommendationRepository extends RepositoryBase<Recommendation> implements IRecommendationRepository {

    private Vector<Recommendation> recommendations;

    public RecommendationRepository(){ this.recommendations = new Vector<>(this.loadData());}

    public Recommendation insert(Recommendation recommendation) {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        Recommendation recommendResult = recommendations.stream().filter(tempRecommend ->
                tempRecommend.getUserID() == recommendation.getUserID() &&
                        tempRecommend.getBookID() == recommendation.getBookID())
                .findFirst().orElse(null);

        if (recommendResult == null){
            recommendations.add(recommendation);
            this.saveData(recommendations);
            return recommendation;
        }

        return null;
    }

    public Recommendation update (Recommendation recommendation)
    {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        Recommendation recommendationResult = recommendations.stream().filter(rcmd ->
                rcmd.getUserID() == recommendation.getUserID() && rcmd.getBookID() == recommendation.getBookID())
                .findFirst().orElse(null);

        if(recommendationResult == null)
            return null;

        recommendations.remove(recommendationResult);

        boolean result = recommendations.add(recommendation);

        if (result)
        {
            this.saveData(recommendations);
            return recommendation;
        }
        else{
            return null;
        }
    }


    public Recommendation fetchRecommendationByUserIDBookID(int userID, int bookID)
    {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        Recommendation rcmd = recommendations.stream().filter(recommendation ->
                recommendation.getUserID() == userID && recommendation.getBookID() == bookID)
                .findFirst().orElse(null);

        return rcmd;
    }

    public Recommendation fetchRecommendationByUserID(int userID)
    {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        Recommendation rcmd = recommendations.stream().filter(recommendation ->
                recommendation.getUserID() == userID)
                .findFirst().orElse(null);

        return rcmd;
    }

    public Recommendation fetchRecommendationByBookID(int bookID)
    {
        if (recommendations == null || recommendations.isEmpty())
            return null;

        Recommendation rcmd = recommendations.stream().filter(recommendation ->
                recommendation.getBookID() == bookID)
                .findFirst().orElse(null);

        return rcmd;
    }
}
