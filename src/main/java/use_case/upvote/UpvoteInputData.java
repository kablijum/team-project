package use_case.upvote;

import entity.Review;
import entity.User;

public class UpvoteInputData {
    final String username;
    final String reviewUsername;
    final int songId;

    public UpvoteInputData(String username, String reviewUsername, int songId) {
        this.username = username;
        this.reviewUsername = reviewUsername;
        this.songId = songId;
    }

    public String getUsername() {return username;}
    public String getReviewUsername() {return reviewUsername;}
    public int getSongId() {return songId;}
}
