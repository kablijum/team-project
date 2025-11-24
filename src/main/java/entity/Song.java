package entity;

import java.util.ArrayList;
import java.util.List;

public class Song {

    /**
     * Creates a new song with the given id, name, artist, rating, reviews, and upvotes.
     * @param id unique song id
     * @param name song name
     * @param artist song artist
     * @param rating song rating (1-5)
     * @param reviews list of the song's reviews
     */
    private int id;
    private final String name;
    private final String artist;
    private double averageRating;
    private List<Review> reviews;

    public Song(int id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.reviews = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getArtist() {
        return artist;
    }
    public double getAverageRating() {
        return averageRating;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void addReview(Review review) {
        reviews.add(review);
        updateAverageRating();
    }
    public void deleteReview(Review review) {
        reviews.remove(review);
    }
    public void upvote(Review review) {
        // Update the upvoted review under this song
        assert this.getId() == review.getSongID();
        List<Review> reviews = this.getReviews();
        this.reviews = new ArrayList<>();
        for (Review songreview : reviews) {
            if (songreview.getUsername().equals(review.getUsername())) {
                songreview.addUpvote();
            }
            this.addReview(songreview);
        }
    }
    public void removeUpvote(Review review) {
        // Remove the upvote of the review written about this song
        assert this.getId() == review.getSongID();
        List<Review> reviews = this.getReviews();
        this.reviews = new ArrayList<>();
        for (Review songreview : reviews) {
            if (songreview.getUsername().equals(review.getUsername())) {
                songreview.removeUpvote();
            }
            this.addReview(songreview);
        }
    }

    public void updateAverageRating() {
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        this.averageRating = (double) sum / reviews.size();
    }
}
