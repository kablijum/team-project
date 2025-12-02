package use_case.login;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * Test presenter for the Login use case.
 * Implements LoginOutputBoundary and records what was passed in.
 */
public class LoginPresenterTest implements LoginOutputBoundary {

    private boolean successViewCalled = false;
    private boolean failViewCalled = false;
    private LoginOutputData outputData;
    private String errorMessage;

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        this.successViewCalled = true;
        this.outputData = loginOutputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.failViewCalled = true;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccessViewCalled() {
        return successViewCalled;
    }

    public boolean isFailViewCalled() {
        return failViewCalled;
    }

    public LoginOutputData getOutputData() {
        return outputData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
