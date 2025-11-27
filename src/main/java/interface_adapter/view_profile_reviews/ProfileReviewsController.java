package interface_adapter.view_profile_reviews;

import interface_adapter.ViewManagerModel;
import view.HomeView;
import view.UserProfileView;

public class ProfileReviewsController {

    private final ViewManagerModel viewManagerModel;

    public ProfileReviewsController(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    public void openProfile() {
        viewManagerModel.setState(UserProfileView.VIEW_NAME);
        viewManagerModel.firePropertyChange();
    }

    public void goBackToHome() {
        viewManagerModel.setState(HomeView.VIEW_NAME);
        viewManagerModel.firePropertyChange();
    }

    public void editReviewAt(int index) {
        // TODO: implement delete review use case
    }

    public void changePassword() {
        // TODO: implement change password use case
    }
}