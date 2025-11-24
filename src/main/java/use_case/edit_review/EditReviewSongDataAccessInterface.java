package use_case.edit_review;

import entity.Review;

public interface EditReviewSongDataAccessInterface {

    boolean existsByUsername(String username, int songId);

    Review getReview(String username, int songId);

    void updateReview(Review review, int songId);

    double getAverageRating(int songId);

    String getSongName(int songId);
}
