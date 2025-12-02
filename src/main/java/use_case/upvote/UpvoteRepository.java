
package use_case.upvote;

public interface UpvoteRepository {
    /**
     * UpvoteRepository interface for facade design pattern.
     * @param username the username of user who upvoted the review.
     * @param reviewUsername the username of the upvoted review.
     * @param songId the song id where upvoted review is written of.
     * @return iff the user is upvoting the review.
     */
    boolean toggleUpvote(String username,
                         String reviewUsername, int songId);
}
