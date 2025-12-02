package interface_adapter.upvote_review;

import use_case.upvote.UpvoteOutputData;
import use_case.upvote.UpvoteOutputDataBoundary;

public class UpvotePresenter implements UpvoteOutputDataBoundary {

    private UpvoteViewModel upvoteViewModel;

    public UpvotePresenter(final UpvoteViewModel viewModel) {
        this.upvoteViewModel = viewModel;
    }

    @Override
    public final void prepareSuccessView(final UpvoteOutputData outputData) {
        UpvoteState upvoteState = upvoteViewModel.getState();

        upvoteState.setUsername(outputData.getUsername());
        upvoteState.setUserUpvoted(outputData.isUpvoted());
        upvoteState.setSongId(outputData.getSongId());
        upvoteState.setReviewUsername(outputData.getReviewUsername());
    }
}
