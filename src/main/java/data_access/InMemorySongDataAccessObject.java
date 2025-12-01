package data_access;

import entity.Review;
import entity.Song;
import use_case.post_review.PostReviewSongDataAccessInterface;
import use_case.upvote.UpvoteSongDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySongDataAccessObject implements
                                            PostReviewSongDataAccessInterface,
                                            UpvoteSongDataAccessInterface {
    private final Map<String, Song> songs = new HashMap<>();

    @Override
    public final void saveSong(final Song song) {
        songs.put(Integer.toString(song.getId()), song);
    }

    public final Song get(final int songId) {
        return songs.get(Integer.toString(songId));
    }

    @Override
    public final boolean existsByUsername(final String username,
                                          final int songid) {
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
    public final void addReview(final Review review, final int songId) {
        Song song = this.getSongById(songId);
        song.addReview(review);

    }

    @Override
    public final Song getSongById(final int songId) {
        return songs.get(Integer.toString(songId));

    }

    @Override
    public final void upvoteReview(final String reviewUsername,
                                   final int songId) {
        Song reviewedSong = songs.get(Integer.toString(songId));
        List<Review> reviews = reviewedSong.getReviews();
        for (Review review : reviews) {
            if (review.getUsername().equals(reviewUsername)) {
                review.addUpvote();
            }
        }
    }

    @Override
    public final void downvoteReview(final String reviewUsername,
                                     final int songId) {
        Song reviewedSong = songs.get(Integer.toString(songId));
        List<Review> reviews = reviewedSong.getReviews();
        for (Review review : reviews) {
            if (review.getUsername().equals(reviewUsername)) {
                review.removeUpvote();
            }
        }
    }

    public Review getReview(final String username, final int songId) {
        Song song = songs.get(Integer.toString(songId));
        for (Review review : song.getReviews()) {
            if (review.getUsername().equals(username)) {
                return review;
            }
        }
        return null;
    }
}
