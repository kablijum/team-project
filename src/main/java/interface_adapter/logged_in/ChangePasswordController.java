package interface_adapter.logged_in;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param username the user whose password to change
     * @param password the new password
     */
    public void execute(String username, String password) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}
