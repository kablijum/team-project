package use_case.upvote;

public class UpvoteOutputData {
    private final String username;
    private final String reviewUsername;
    private final int songId;
    private final boolean isUpvoted;

    /**
     * Construct the output data for the upvote use case.
     * @param usernameOutput of the user who upvoted a review.
     * @param reviewUsernameOutput is the username of the user who wrote the review.
     * @param songIdOutput is the id of the song where the review is written
     * @param isUpvotedOutput iff the user is upvoting the review.
     */
    public UpvoteOutputData(final String usernameOutput,
                            final String reviewUsernameOutput,
                            final int songIdOutput,
                            final boolean isUpvotedOutput) {
        this.username = usernameOutput;
        this.reviewUsername = reviewUsernameOutput;
        this.songId = songIdOutput;
        this.isUpvoted = isUpvotedOutput;
    }

    public String getUsername() {
        return username;
    }
    public String getReviewUsername() {
        return reviewUsername;
    }
    public int getSongId() {
        return songId;
    }
    public boolean isUpvoted() {
        return isUpvoted;
    }
}
