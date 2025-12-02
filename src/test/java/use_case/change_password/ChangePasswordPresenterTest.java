package use_case.change_password;

import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * Test presenter for the Change Password use case.
 * Implements ChangePasswordOutputBoundary and records what was passed in.
 */
public class ChangePasswordPresenterTest implements ChangePasswordOutputBoundary {

    private boolean successViewCalled = false;
    private boolean failViewCalled = false;
    private ChangePasswordOutputData outputData;
    private String errorMessage;

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        this.successViewCalled = true;
        this.outputData = outputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.failViewCalled = true;
        this.errorMessage = errorMessage;
    }

    // Getters for assertions
    public boolean isSuccessViewCalled() {
        return successViewCalled;
    }

    public boolean isFailViewCalled() {
        return failViewCalled;
    }

    public ChangePasswordOutputData getOutputData() {
        return outputData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
