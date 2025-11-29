package data_access;

import entity.Review;
import entity.Song;
import use_case.post_review.PostReviewSongDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemorySongDataAccessObject implements PostReviewSongDataAccessInterface {
    private final Map<String, Song> songs = new HashMap<>();

    @Override
    public void saveSong(Song song) {
        songs.put(Integer.toString(song.getId()), song);
    }

    public Song get(int songid) {
        return songs.get(Integer.toString(songid));
    }

    @Override
    public boolean existsByUsername(String username, int songid) {
        if (songs.containsKey(Integer.toString(songid))) {
            Song song = songs.get(Integer.toString(songid));
            for (Review review : song.getReviews()) {
                if (review.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void addReview(Review review, int songid) {
        Song song = this.getSongById(songid);
        song.addReview(review);

    }

    @Override
    public Song getSongById(int songid) {
        return songs.get(Integer.toString(songid));

    }
}
