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
     * @throws IllegalArgumentException if the password or name are empty
     */
    private int id;
    private final String name;
    private final String artist;
    private int rating;
    private List<Review> reviews;

    public Song(int id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.rating = 0;
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
    public double getRating() {
        return rating;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void addReview(Review review) {
        reviews.add(review);
    }
    public void deleteReview(Review review) {
        reviews.remove(review);
    }

}
