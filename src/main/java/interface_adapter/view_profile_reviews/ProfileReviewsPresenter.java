package interface_adapter.view_profile_reviews;

import interface_adapter.ViewManagerModel;

import use_case.view_profile.ViewProfileOutputBoundary;
import use_case.view_profile.ViewProfileOutputData;
import view.UserProfileView;


public class ProfileReviewsPresenter implements ViewProfileOutputBoundary {
    /** The ViewModel to be updated with profile review data. */
    private final ProfileReviewsViewModel viewModel;
    /** The View Manager Model to handle view switching. */
    private final ViewManagerModel viewManagerModel;

    public ProfileReviewsPresenter(final ProfileReviewsViewModel viewModel,
                                   final ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }
    /**
     * Presents the output data by updating the ViewModel and switching to the user profile view.
     * * @param data the output data containing profile information and reviews
     */
    @Override
    public void present(final ViewProfileOutputData data) {
        viewModel.setUsername(data.getUsername());
        var rows = data.getReviews().stream()
                .map(r -> new ProfileReviewsViewModel.ReviewRow(
                        r.getSongID(),
                        r.getSongTitle(),
                        r.getRating(),
                        r.getComment()
                ))
                .collect(java.util.stream.Collectors.toList());
        viewModel.setReviews(rows);

        viewModel.firePropertyChange("state");

        viewManagerModel.setState(UserProfileView.VIEW_NAME);
        viewManagerModel.firePropertyChange();
        }
}
