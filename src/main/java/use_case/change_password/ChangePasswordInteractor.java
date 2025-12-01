package use_case.change_password;

import entity.User;
import entity.UserFactory;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        String username = changePasswordInputData.getUsername();
        String newPassword = changePasswordInputData.getPassword();

        if (username == null || username.isEmpty()) {
            userPresenter.prepareFailView("Username is required");
            return;
        }
        if (newPassword == null || newPassword.isEmpty()) {
            userPresenter.prepareFailView("New password cannot be empty");
            return;
        }

        if (!userDataAccessObject.existsByName(username)) {
            userPresenter.prepareFailView("User does not exist: " + username);
            return;
        }

        User user = userFactory.create(username, newPassword);
        userDataAccessObject.changePassword(user);

        User reloaded = userDataAccessObject.get(username);
        System.out.println("DEBUG after changePassword: username=" + username
                + ", password-from-DB='" + reloaded.getPassword() + "'");

        // 6. Presenter success
        ChangePasswordOutputData outputData =
                new ChangePasswordOutputData(user.getUsername());
        userPresenter.prepareSuccessView(outputData);
    }

}
