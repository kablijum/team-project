package use_case.post_review;

import entity.Review;
import entity.User;

public interface PostReviewUserDataAccessInterface {

    void addReview(Review review, String username);

    void save(User user);
}

