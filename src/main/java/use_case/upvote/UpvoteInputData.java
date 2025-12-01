package use_case.upvote;


public class UpvoteInputData {
    private final String username;
    private final String reviewUsername;
    private final int songId;

    /**
     * Construct the UpvoteInput data.
     * @param usernameInput of the user who upvoted a review.
     * @param reviewUsernameInput is the username of the user who wrote the review.
     * @param songIdInput is the id of the song where the review is written.
     */
    public UpvoteInputData(final String usernameInput,
                           final String reviewUsernameInput,
                           final int songIdInput) {
        this.username = usernameInput;
        this.reviewUsername = reviewUsernameInput;
        this.songId = songIdInput;
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
}
