package use_case.signup;

import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * Test presenter for the Signup use case.
 * Implements SignupOutputBoundary and records what was passed in.
 */
public class SignupPresenterTest implements SignupOutputBoundary {

    private boolean successViewCalled = false;
    private boolean failViewCalled = false;
    private boolean switchToLoginViewCalled = false;
    private SignupOutputData outputData;
    private String errorMessage;

    @Override
    public void prepareSuccessView(SignupOutputData signupOutputData) {
        this.successViewCalled = true;
        this.outputData = signupOutputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.failViewCalled = true;
        this.errorMessage = errorMessage;
    }

    @Override
    public void switchToLoginView() {
        this.switchToLoginViewCalled = true;
    }

    // Getters for assertions in tests
    public boolean isSuccessViewCalled() {
        return successViewCalled;
    }

    public boolean isFailViewCalled() {
        return failViewCalled;
    }

    public boolean isSwitchToLoginViewCalled() {
        return switchToLoginViewCalled;
    }

    public SignupOutputData getOutputData() {
        return outputData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
