
package data_access;

import entity.Review;
import entity.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.post_review.PostReviewUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.upvote.UpvoteUserDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        PostReviewUserDataAccessInterface,
        UpvoteUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();

    private String currentUsername;

    @Override
    public final boolean existsByName(final String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public final void save(final User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public final User get(final String username) {
        return users.get(username);
    }

    @Override
    public final void setCurrentUsername(final String name) {
        currentUsername = name;
    }

    @Override
    public final String getCurrentUsername() {
        return currentUsername;
    }

    @Override
    public final void changePassword(final User user) {
        // Replace the old entry with the new password
        users.put(user.getUsername(), user);
    }

    @Override
    public final void addReview(final Review review, final String username) {
        User user = users.get(username);
        user.addWrittenReview(review);
    }

    @Override
    public final void upvoteReview(final String username,
                                   final String reviewUsername,
                                   final int songId) {
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
    public final void downvoteReview(final String username,
                                     final String reviewUsername,
                                     final int songId) {
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
    public final boolean isUpvoted(final String username,
                                   final String reviewUsername,
                                   final int songId) {
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
}
