package use_case.post_review;

import entity.Review;
import entity.Song;

public interface PostReviewSongDataAccessInterface {

    Song getSongbyID(int songID);

    boolean existsByUsername(String username, int songid);

    void addReview(Review review, int songid);

    Song getSongByID(int songid);
}
