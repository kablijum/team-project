package use_case.edit_review;

import entity.Review;

public interface EditReviewUserDataAccesssInterface {

    void updateReview(Review review, String username);
}
