package interface_adapter.upvote_review;

public class UpvoteState {
    private String username;
    private boolean userUpvoted;
    private String reviewUsername;
    private int songId;

    public void setUsername(final String usernameState) {
        this.username = usernameState;
    }
    public void setUserUpvoted(final boolean userUpvotedState) {
        this.userUpvoted = userUpvotedState;
    }
    public void setReviewUsername(final String reviewUsernameState) {
        this.reviewUsername = reviewUsernameState;
    }
    public void setSongId(final int songIdState) {
        this.songId = songIdState;
    }

    public String getUsername() {
        return username;
    }
    public boolean isUserUpvoted() {
        return userUpvoted;
    }
    public String getReviewUsername() {
        return reviewUsername;
    }
    public int getSongId() {
        return songId;
    }
}
