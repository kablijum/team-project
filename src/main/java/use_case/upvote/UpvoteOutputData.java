package use_case.upvote;

import entity.Review;
import entity.User;

public class UpvoteOutputData {
    final User user;
    final Review review;

    public UpvoteOutputData(User user, Review review) {
        this.user = user;
        this.review = review;
    }

    public User getUser() {
        return user;
    }
    public Review getReview() {
        return review;
    }
}
