package interface_adapter.view_profile_reviews;

import interface_adapter.ViewManagerModel;

import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logout.LogoutController;
import view.HomeView;
import view.UserProfileView;

public class ProfileReviewsController {

    private final ViewManagerModel viewManagerModel;
    private final LogoutController logoutController;
    private final ChangePasswordController changePasswordController;

    public ProfileReviewsController(ViewManagerModel viewManagerModel,
                                    LogoutController logoutController,
                                    ChangePasswordController changePasswordController) {
        this.viewManagerModel = viewManagerModel;
        this.logoutController = logoutController;
        this.changePasswordController = changePasswordController;
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

    public void changePassword() {
        // TODO: implement change password use case
    public void editReviewAt(int index) {
        // TODO: implement delete review use case
    }

    public void changePassword(String newPassword, String username) {
        changePasswordController.execute(username, newPassword);
    }
}