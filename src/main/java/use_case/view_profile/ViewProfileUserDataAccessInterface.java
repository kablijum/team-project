package use_case.view_profile;

import entity.Review;
import java.util.List;

/**
 * Data access contract for fetching user-specific data needed for profile viewing.
 */
public interface ViewProfileUserDataAccessInterface {
    /**
     * Retrieves all reviews written by a specific user.
     * @param username the username
     * @return a list of Review entities
     */
    List<Review> getUserReviews(String username);
}
