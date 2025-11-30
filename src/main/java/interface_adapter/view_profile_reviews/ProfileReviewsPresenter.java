package interface_adapter.view_profile_reviews;

import interface_adapter.ViewManagerModel;

import use_case.view_profile.ViewProfileOutputBoundary;
import use_case.view_profile.ViewProfileOutputData;


public class ProfileReviewsPresenter implements ViewProfileOutputBoundary {
    private final ProfileReviewsViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public ProfileReviewsPresenter(ProfileReviewsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void present(ViewProfileOutputData data) {
        viewModel.setUsername(data.getUsername());
        var rows = data.getReviews().stream()
                .map(r -> new ProfileReviewsViewModel.ReviewRow(
                        r.getSongID(),
                        r.getRating(),
                        r.getComment()
                ))
                .collect(java.util.stream.Collectors.toList());
        viewModel.setReviews(rows);

        viewManagerModel.setState(ProfileReviewsViewModel.class.getSimpleName());
        viewManagerModel.firePropertyChange();
        }
}
