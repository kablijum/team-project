package use_case.view_profile;

import entity.Review;
import java.util.List;

public interface ViewProfileUserDataAccessInterface {
    String getCurrentUsername();
    List<Review> getUserReviews(String username);
}
