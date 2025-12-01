package use_case.upvote;

import interface_adapter.upvote_review.UpvotePresenter;
import use_case.post_review.PostOutputData;

public class TestUpvotePresenter implements UpvoteOutputDataBoundary {
    private boolean successViewCalled = false;
    private UpvoteOutputData outputData;

    @Override
    public void prepareSuccessView(UpvoteOutputData upvoteOutputData) {
        this.successViewCalled = true;
        this.outputData = upvoteOutputData;
    }

    public boolean isSuccessViewCalled() {
        return successViewCalled;
    }
    public UpvoteOutputData getOutputData() {
        return outputData;
    }
}
