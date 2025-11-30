package entity;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * An entity representing a user. Users have a username, a password, a list of all reviews they've posted, on any given song profile, and a set of all Reviews from other users which they have upvoted.
 */

public class User {

    private final String username;
    private final String password;
    private final List<Review> writtenReviews;
    private final Set<Review> upvotedReviews;

    /**
     * Create a new User with a given non-empty password and non-empty name.
     * @param username the unique username chosen by the user
     * @param password the password chosen by the user
     * @throws IllegalArgumentException if the password or name are empty
     */
    public User (String username, String password) {
        if ("".equals(username)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if ("".equals(password)) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.username = username;
        this.password = password;
        this.writtenReviews = new ArrayList<>();
        this.upvotedReviews = new HashSet<>();
    }
    // --------- Getters ---------
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public List<Review> getWrittenReviews() {
        return writtenReviews;
    }
    public Set<Review> getUpvotedReviews() {
        return upvotedReviews;
    }
    // --------- Setters  ---------
    public void addWrittenReview(Review review) {
        if (!writtenReviews.contains(review)) {
            writtenReviews.add(review);
        }
    }
    public void upvoteReview(Review review) {
        upvotedReviews.add(review);
    }

    public void removeUpvote(Review review) {
        upvotedReviews.remove(review);
    }

    public boolean hasUpvoted(Review review) {
        return upvotedReviews.contains(review);
    }
}
