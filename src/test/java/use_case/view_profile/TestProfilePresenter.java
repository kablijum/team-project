package use_case.view_profile;

public class TestProfilePresenter implements ViewProfileOutputBoundary {
    private boolean successViewCalled = false;
    private ViewProfileOutputData outputData;

    @Override
    public void present(ViewProfileOutputData outputData) {
        this.successViewCalled = true;
        this.outputData = outputData;
    }

    public boolean isSuccessViewCalled() {
        return successViewCalled;
    }

    public ViewProfileOutputData getOutputData() {
        return outputData;
    }
}
