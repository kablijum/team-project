package use_case.post_review;

import entity.Review;

public interface PostReviewUserDataAccessInterface {

    void addReview(Review review, String Username);
}

