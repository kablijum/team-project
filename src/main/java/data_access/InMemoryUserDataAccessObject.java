package data_access;

import entity.Review;
import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.edit_review.EditUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.post_review.PostReviewUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.upvote.UpvoteUserDataAccessInterface;
import use_case. view_profile.ViewProfileUserDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
                                                     LoginUserDataAccessInterface,
                                                     ChangePasswordUserDataAccessInterface,
                                                     LogoutUserDataAccessInterface, PostReviewUserDataAccessInterface,
                                                     UpvoteUserDataAccessInterface, ViewProfileUserDataAccessInterface,
                                                     EditUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void setCurrentUsername(String name) {
        currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getUsername(), user);
    }

    @Override
    public void addReview(Review review, String username) {
        User user = users.get(username);
        user.addWrittenReview(review);
    }

    @Override
    public void upvoteReview(String username, String reviewUsername, int songId) {
        User upvotedUser = users.get(username);
        User reviewUser = users.get(reviewUsername);
        List<Review> reviews = reviewUser.getWrittenReviews();
        Review review = null;
        for (Review r : reviews) {
            if (r.getUsername().equals(reviewUsername)) {
                review = r;
            }
        }
        upvotedUser.upvoteReview(review);
    }

    @Override
    public void downvoteReview(String username, String reviewUsername, int songId) {
        User upvotedUser = users.get(username);
        User reviewUser = users.get(reviewUsername);
        List<Review> reviews = reviewUser.getWrittenReviews();
        Review review = null;
        for (Review r : reviews) {
            if (r.getUsername().equals(reviewUsername)) {
                review = r;
            }
        }
        upvotedUser.removeUpvote(review);
    }

    @Override
    public boolean isUpvoted(String username, String reviewUsername, int songId) {
        User upvotedUser = users.get(username);
        User reviewUser = users.get(reviewUsername);
        List<Review> reviews = reviewUser.getWrittenReviews();
        Review review = null;
        for (Review r : reviews) {
            if (r.getSongID() == songId) {
                review = r;
            }
        }
        return upvotedUser.hasUpvoted(review);
    }

    @Override
    public List<Review> getUserReviews(String username) {
        User user = users.get(username);
        if (user != null) {
            return user.getWrittenReviews();
        }
        return new java.util.ArrayList<>();
    }

    @Override
    public void updateUser(User user) {  // <-- ADDED THIS METHOD
        users.put(user.getUsername(), user);
    }
}
