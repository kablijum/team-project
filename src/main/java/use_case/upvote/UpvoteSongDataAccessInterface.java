package use_case.upvote;

public interface UpvoteSongDataAccessInterface {
    /**
     * Reflect the upvote action in the song database.
     * @param reviewUsername the username of the upvoted review.
     * @param songId the songid where upvoted review is written of.
     */
    void upvoteReview(String reviewUsername, int songId);
    /**
     * Reflect the downvote action in the song database.
     * @param reviewUsername the username of the upvoted review.
     * @param songId the songid where upvoted review is written of.
     */
    void downvoteReview(String reviewUsername, int songId);
}
