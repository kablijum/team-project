package data_access;

import entity.Review;
import entity.Song;
import use_case.edit_review.EditSongDataAccessInterface;
import use_case.post_review.PostReviewSongDataAccessInterface;
import use_case.upvote.UpvoteSongDataAccessInterface;
import use_case.view_profile.ViewProfileSongDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySongDataAccessObject implements PostReviewSongDataAccessInterface, UpvoteSongDataAccessInterface,
        ViewProfileSongDataAccessInterface, EditSongDataAccessInterface {
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

    @Override
    public void upvoteReview(String reviewUsername, int songId) {
        Song reviewedSong = songs.get(Integer.toString(songId));
        List<Review> reviews = reviewedSong.getReviews();
        for (Review review : reviews) {
            if (review.getUsername().equals(reviewUsername)) {
                review.addUpvote();
            }
        }
    }

    @Override
    public void downvoteReview(String reviewUsername, int songId) {
        Song reviewedSong = songs.get(Integer.toString(songId));
        List<Review> reviews = reviewedSong.getReviews();
        for (Review review : reviews) {
            if (review.getUsername().equals(reviewUsername)) {
                review.removeUpvote();
            }
        }
    }

    public Review getReview(String username, int songid) {
        Song song = songs.get(Integer.toString(songid));
        for (Review review : song.getReviews()) {
            if (review.getUsername().equals(username)) {
                return review;
            }
        }
        return null;
    }
}
