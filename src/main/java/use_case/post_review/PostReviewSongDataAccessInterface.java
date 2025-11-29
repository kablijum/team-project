package use_case.post_review;

import entity.Review;
import entity.Song;

public interface PostReviewSongDataAccessInterface {

    boolean existsByUsername(String username, int songid);

    void addReview(Review review, int songid);

    Song getSongById(int songid);

    void saveSong(Song song);
}
