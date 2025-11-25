package interface_adapter.upvote_review;

public class UpvoteState {
    private String username;
    private int upvoteCount;
    private boolean userUpvoted;
    private String reviewUsername;
    private int songId;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setUpvoteCount(int upvoteCount) {
        this.upvoteCount = upvoteCount;
    }
    public void setUserUpvoted(boolean userUpvoted) {
        this.userUpvoted = userUpvoted;
    }
    public void setReviewUsername(String reviewUsername) {
        this.reviewUsername = reviewUsername;
    }
    public void setSongId(int songId) {
        this.songId = songId;
    }
}
