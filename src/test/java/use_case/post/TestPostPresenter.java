package use_case.post;

import use_case.post_review.PostOutputData;
import use_case.post_review.PostOutputDataBoundary;

public class TestPostPresenter implements PostOutputDataBoundary {
    private boolean successViewCalled = false;
    private boolean failViewCalled = false;
    private PostOutputData outputData;
    private String errorMessage;

    @Override
    public void prepareSuccessView(PostOutputData postOutputData) {
        this.successViewCalled = true;
        this.outputData = postOutputData;
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
    public PostOutputData getOutputData() {
        return outputData;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}
