package use_case.upvote;

import entity.Review;
import entity.User;

public interface UpvoteUserDataAccessInterface {
    /**
     * Checks if the given username exists.
     * @param username the username of user who upvoted the review.
     * @param reviewUsername the username of the upvoted review.
     * @param songId the songid where upvoted review is written of.
     */
    void upvoteReview(String username, String reviewUsername, int songId);
}
