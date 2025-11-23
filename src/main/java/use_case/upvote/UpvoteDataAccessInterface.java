package use_case.upvote;

import entity.Review;
import entity.User;

public interface UpvoteDataAccessInterface {
    /**
     * Checks if the given username exists.
     * @param user the username of the user upvoting a review.
     * @param review the upvoted review
     */
    void upvoteReview(User user, Review review);
}
