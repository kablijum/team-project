package use_case.post_review;

import entity.Review;

public interface PostReviewSongDataAccessInterface {

    boolean existsByUsername(String username, int songid);

    void addReview(Review review, int songid);

    String getSongName(int songid);

}
