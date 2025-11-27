package use_case.upvote;

public class UpvoteOutputData {
    final String username;
    final String reviewUsername;
    final int songId;
    final boolean isUpvoted;

    public UpvoteOutputData(String username, String reviewUsername, int songId,  boolean isUpvoted) {
        this.username = username;
        this.reviewUsername = reviewUsername;
        this.songId = songId;
        this.isUpvoted = isUpvoted;
    }

    public String getUsername() {return username;}
    public String getReviewUsername() {return reviewUsername;}
    public int getSongId() {return songId;}
    public boolean isUpvoted() {return isUpvoted;}
}
