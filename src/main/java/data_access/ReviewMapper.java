package data_access;

import entity.Review;
import org.json.JSONObject;

/**
 *  This class takes in a review data from the database as a form of JSONObject and returns Review object and vice versa.
 */
public class ReviewMapper {
    private final JSONObject reviewJSONObject;
    private final Review review;
    private static final String USERNAME = "username";
    private static final String COMMENT = "comment";
    private static final String SONGID = "songid";
    private static final String RATING = "rating";
    private static final String UPVOTES = "upvotes";

    public ReviewMapper(JSONObject reviewJSONObject) {
        this.reviewJSONObject = reviewJSONObject;
        this.review = null;
    }

    public ReviewMapper(Review review) {
        this.reviewJSONObject = null;
        this.review = review;
    }

    public Review mapJSONtoReview() {
        assert reviewJSONObject != null;
        String author = reviewJSONObject.getString(USERNAME);
        String comment = reviewJSONObject.getString(COMMENT);
        int songid = reviewJSONObject.getInt(SONGID);
        int rating = reviewJSONObject.getInt(RATING);
        int upvotes = reviewJSONObject.getInt(UPVOTES);
        return new Review(author, comment, songid, rating, upvotes);
    }

    public JSONObject mapJReviewtoJSON() {
        assert review != null;
        assert reviewJSONObject != null;
        JSONObject reviewJSON = new JSONObject();
        reviewJSONObject.put(USERNAME, review.getUsername());
        reviewJSONObject.put(COMMENT, review.getComment());
        reviewJSONObject.put(SONGID, review.getSong());
        reviewJSONObject.put(RATING, review.getRating());
        reviewJSONObject.put(UPVOTES, review.getUpvotes());
        return reviewJSON;
    }
}
