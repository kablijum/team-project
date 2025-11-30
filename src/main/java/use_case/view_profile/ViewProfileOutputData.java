package use_case.view_profile;

import java.util.List;

public class ViewProfileOutputData {
    private final List<ViewProfileReviewData> reviews;
    private final String username;

    public ViewProfileOutputData(String username, List<ViewProfileReviewData> reviews) {
        this.username = username;
        this.reviews = reviews;
    }

    public String getUsername() { return username; }
    public List<ViewProfileReviewData> getReviews() { return reviews; }
}
