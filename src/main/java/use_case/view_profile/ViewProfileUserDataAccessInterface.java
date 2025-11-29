package use_case.view_profile;

import java.util.List;

public interface ViewProfileUserDataAccessInterface {
    List<ViewProfileReviewData> getUserReviews(String username);
}
