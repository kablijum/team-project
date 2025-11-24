package interface_adapter.upvote_review;

import use_case.upvote.UpvoteOutputData;
import use_case.upvote.UpvoteOutputDataBoundary;

public class UpvotePresenter implements UpvoteOutputDataBoundary {

    @Override
    public void prepareSuccessView(UpvoteOutputData outputData) {
    }
}
