package use_case.upvote;

import entity.Review;

public class UpvoteInputData {
    final String username;
    final Review review;

    public UpvoteInputData(String username,Review review) {
        this.username = username;
        this.review = review;
    }

    public String getUserName() {
        return username;
    }

    public Review getReview() {
        return review;
    }
}
