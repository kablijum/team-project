package use_case.view_profile;

import java.util.List;

public class ViewProfileOutputData {
    /** The list of reviews for the profile. */
    private final List<ViewProfileReviewData> reviews;
    /** The username of the profile viewed. */
    private final String username;

    public ViewProfileOutputData(final String username,
                                 final List<ViewProfileReviewData> reviews) {
        this.username = username;
        this.reviews = reviews;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username; }
    /**
     * @return the list of reviews
     */
    public List<ViewProfileReviewData> getReviews() {
        return reviews; }
}
