package interface_adapter.view_profile_reviews;
import interface_adapter.ViewManagerModel;

import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logout.LogoutController;
import use_case.view_profile.ViewProfileInputBoundary;
import use_case.view_profile.ViewProfileInputData;
import view.HomeView;

public class ProfileReviewsController {

    /** The View Manager Model to handle view switching. */
    private final ViewManagerModel viewManagerModel;
    /** The controller used to handle logout operations. */
    private final LogoutController logoutController;
    /** The controller used to handle password changes. */
    private final ChangePasswordController changePasswordController;
    /** The input boundary for the View Profile use case. */
    private final ViewProfileInputBoundary viewProfileInteractor;
//    private final LoggedInViewModel loggedInViewModel;
    /**
     * Constructs a new ProfileReviewsController.
     *
     * @param viewManagerModel       the view manager model
     * @param logoutController       the logout controller
     * @param changePasswordController the change password controller
     * @param viewProfileInteractor  the interactor for viewing profiles
     */
    public ProfileReviewsController(final ViewManagerModel viewManagerModel,
                                    final LogoutController logoutController,
                                    final ChangePasswordController
                                            changePasswordController,
                                    final ViewProfileInputBoundary
                                            viewProfileInteractor) {
        this.viewManagerModel = viewManagerModel;
        this.logoutController = logoutController;
        this.changePasswordController = changePasswordController;
        this.viewProfileInteractor = viewProfileInteractor;
//        this.loggedInViewModel = loggedInViewModel;
    }
    /**
     * Initiates the process to open a specific user's profile.
     *
     * @param username the username of the profile to view
     */
    public void openProfile(final String username) {
//        String username = loggedInViewModel.getState().getUsername();

        ViewProfileInputData input = new ViewProfileInputData(username);
        viewProfileInteractor.execute(input);

//        viewManagerModel.setState(UserProfileView.VIEW_NAME);
//        viewManagerModel.firePropertyChange();
    }
    /**
     * Navigates the user back to the Home View.
     */
    public void goBackToHome() {
        viewManagerModel.setState(HomeView.VIEW_NAME);
        viewManagerModel.firePropertyChange();
    }
    /**
     * Executes the logout process.
     */
    public void logout() {
        // delegate to the real LogoutController / use case
        logoutController.execute();
    }
    /**
     * Initiates the password change process.
     *
     * @param newPassword the new password desired by the user
     * @param username    the username of the user changing the password
     */
    public void changePassword(final String newPassword,
                               final String username) {
        changePasswordController.execute(username, newPassword);
    }
}
