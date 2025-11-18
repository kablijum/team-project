package data_access;

import entity.Review;
import org.json.JSONObject;

/**
 *  This class takes in a review data from the database as a form of JSONObject and returns Review object.
 */
public class ReviewMapper {
    private final JSONObject review;
    private static final String USERNAME = "username";
    private static final String COMMENT = "comment";
    private static final String SONGID = "songid";
    private static final String RATING = "rating";
    private static final String UPVOTES = "upvotes";

    public ReviewMapper(JSONObject review) {
        this.review = review;
    }

    public Review mapReview() {
        String author = review.getString(USERNAME);
        String comment = review.getString(COMMENT);
        String songid = review.getString(SONGID);
        int rating = review.getInt(RATING);
        int upvotes = review.getInt(UPVOTES);
        return new Review(author, comment, songid, rating, upvotes);
    }
}
