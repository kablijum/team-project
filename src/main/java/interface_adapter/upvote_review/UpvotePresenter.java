package interface_adapter.upvote_review;

import entity.Review;
import entity.User;
import use_case.upvote.UpvoteOutputData;
import use_case.upvote.UpvoteOutputDataBoundary;

public class UpvotePresenter implements UpvoteOutputDataBoundary {

    private UpvoteViewModel upvoteViewModel;

    public UpvotePresenter(UpvoteViewModel upvoteViewModel) {
        this.upvoteViewModel = upvoteViewModel;
    }

    @Override
    public void prepareSuccessView(UpvoteOutputData outputData) {
        UpvoteState upvoteState = upvoteViewModel.getState();

        upvoteState.setUsername(outputData.getUsername());
        upvoteState.setUserUpvoted(outputData.isUpvoted());
        upvoteState.setSongId(outputData.getSongId());
        upvoteState.setReviewUsername(outputData.getReviewUsername());
    }
}
