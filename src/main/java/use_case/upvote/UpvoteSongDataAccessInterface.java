package use_case.upvote;

import entity.Review;
import entity.User;

public interface UpvoteSongDataAccessInterface {
    /**
     * Checks if the given username exists.
     * @param reviewUsername the username of the upvoted review.
     * @param songId the songid where upvoted review is written of.
     */
    void upvoteReview(String reviewUsername, int songId);
}
