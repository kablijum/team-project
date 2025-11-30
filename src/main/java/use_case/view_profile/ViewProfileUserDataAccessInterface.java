package use_case.view_profile;

import entity.Review;
import java.util.List;

public interface ViewProfileUserDataAccessInterface {
    List<Review> getUserReviews(String username);
}
