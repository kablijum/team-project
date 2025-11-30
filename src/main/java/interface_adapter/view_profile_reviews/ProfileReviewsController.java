package interface_adapter.view_profile_reviews;

import interface_adapter.ViewManagerModel;

import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import use_case.view_profile.ViewProfileInputBoundary;
import use_case.view_profile.ViewProfileInputData;
import view.HomeView;
import view.UserProfileView;

public class ProfileReviewsController {

    private final ViewManagerModel viewManagerModel;
    private final LogoutController logoutController;
    private final ChangePasswordController changePasswordController;
    private final ViewProfileInputBoundary viewProfileInteractor;
    private final LoggedInViewModel loggedInViewModel;

    public ProfileReviewsController(ViewManagerModel viewManagerModel,
                                    LogoutController logoutController,
                                    ChangePasswordController changePasswordController,
                                    ViewProfileInputBoundary viewProfileInteractor,
                                    LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.logoutController = logoutController;
        this.changePasswordController = changePasswordController;
        this.viewProfileInteractor = viewProfileInteractor;
        this.loggedInViewModel = loggedInViewModel;
    }

    public void openProfile() {
        String username = loggedInViewModel.getState().getUsername();

        ViewProfileInputData input = new ViewProfileInputData(username);
        viewProfileInteractor.execute(input);

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

    public void changePassword(String newPassword, String username) {
        changePasswordController.execute(username, newPassword);
    }
}