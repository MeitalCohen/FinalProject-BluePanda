package entities;

import java.io.Serializable;

public class Recommendation extends Entity {

    private String bookID;
    private String userID;
    private float rate;
    private String recommendDescription;

    public Recommendation(){}

    public Recommendation(String bookID, String userID, float rate, String recommendDescription) {
        this.bookID = bookID;
        this.userID = userID;
        this.rate = rate;
        this.recommendDescription = recommendDescription;
    }

    @Override
    public String toString() {
        return "Recommendation{" +
                "bookID=" + bookID +
                ", userID=" + userID +
                ", rate=" + rate +
                ", recommendDescription='" + recommendDescription + '\'' +
                '}';
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getRecommendDescription() {
        return recommendDescription;
    }

    public void setRecommendDescription(String recommendDescription) {
        this.recommendDescription = recommendDescription;
    }
}
