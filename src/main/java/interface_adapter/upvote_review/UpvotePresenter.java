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
        User user = outputData.getUser();
        Review review = outputData.getReview();

        upvoteState.setUsername(user.getUsername());
        upvoteState.setUserUpvoted(user.hasUpvoted(review));
        upvoteState.setUpvoteCount(review.getUpvotes());
        upvoteState.setSongId(review.getSongID());
        upvoteState.setReviewUsername(review.getUsername());
    }
}
