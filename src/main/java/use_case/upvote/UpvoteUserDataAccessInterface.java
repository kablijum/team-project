package use_case.upvote;

public interface UpvoteUserDataAccessInterface {
    /**
     * Reflect the upvote action in the user database.
     * @param username the username of user who upvoted the review.
     * @param reviewUsername the username of the upvoted review.
     * @param songId the songid where upvoted review is written of.
     */
    void upvoteReview(String username, String reviewUsername, int songId);
    /**
     * Reflect the downvote action in the user database.
     * @param username the username of user who upvoted the review.
     * @param reviewUsername the username of the upvoted review.
     * @param songId the songid where upvoted review is written of.
     */
    void downvoteReview(String username, String reviewUsername, int songId);
    /**
     * Return whether the user is upvoting the review or not (downvoting).
     * @param username the username of user who upvoted the review.
     * @param reviewUsername the username of the upvoted review.
     * @param songId the songid where upvoted review is written of.
     * @return iff the user is upvoting the review.
     */
    boolean isUpvoted(String username, String reviewUsername, int songId);
}
