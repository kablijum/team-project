package use_case.logout;

import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * Test presenter for the Logout use case.
 * Implements LogoutOutputBoundary and records what was passed in.
 */
public class LogoutPresenterTest implements LogoutOutputBoundary {

    private boolean successViewCalled = false;
    private LogoutOutputData outputData;

    @Override
    public void prepareSuccessView(LogoutOutputData outputData) {
        this.successViewCalled = true;
        this.outputData = outputData;
    }

    // Getters for assertions in tests
    public boolean isSuccessViewCalled() {
        return successViewCalled;
    }

    public LogoutOutputData getOutputData() {
        return outputData;
    }
}
