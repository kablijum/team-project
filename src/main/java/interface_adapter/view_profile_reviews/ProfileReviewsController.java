package interface_adapter.view_profile_reviews;

import interface_adapter.ViewManagerModel;

import interface_adapter.logout.LogoutController;
import view.HomeView;
import view.UserProfileView;

public class ProfileReviewsController {

    private final ViewManagerModel viewManagerModel;
    private final LogoutController logoutController;

    public ProfileReviewsController(ViewManagerModel viewManagerModel,
                                    LogoutController logoutController) {
        this.viewManagerModel = viewManagerModel;
        this.logoutController = logoutController;
    }

    public void openProfile() {
        viewManagerModel.setState(UserProfileView.VIEW_NAME);
        viewManagerModel.firePropertyChange();
    }

    public void goBackToHome() {
        viewManagerModel.setState(HomeView.VIEW_NAME);
        viewManagerModel.firePropertyChange();
    }

    public void logout() {
        // delegate to the real LogoutController / use case
        logoutController.execute();
    }

    public void editReviewAt(int index) {
        // TODO: implement delete review use case
    }

    public void changePassword() {
        // TODO: implement change password use case
    }
}