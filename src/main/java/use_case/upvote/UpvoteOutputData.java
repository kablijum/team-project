package use_case.upvote;

import entity.Review;

public class UpvoteOutputData {
    final String username;
    final Review review;

    public UpvoteOutputData(String username, Review review) {
        this.username = username;
        this.review = review;
    }

    public String getUsername() {
        return username;
    }
    public Review getReview() {
        return review;
    }
}
