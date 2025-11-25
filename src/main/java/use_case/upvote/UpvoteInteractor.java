package use_case.upvote;

import entity.Review;
import entity.User;

public class UpvoteInteractor implements UpvoteInputBoundary {
    private final UpvoteSongDataAccessInterface songDataAccessObject;
    private final UpvoteUserDataAccessInterface userDataAccessObject;
    private final UpvoteOutputDataBoundary upvotePresenter;

    public UpvoteInteractor(UpvoteSongDataAccessInterface songDataAccessObject, UpvoteUserDataAccessInterface userDataAccessObject,
                            UpvoteOutputDataBoundary upvotePresenter) {
        this.songDataAccessObject = songDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.upvotePresenter = upvotePresenter;
    }
    @Override
    public void execute(UpvoteInputData upvoteInputData) {
        // DAOs do not modify the InputData so we need to update it here.
        songDataAccessObject.upvoteReview(upvoteInputData.getUser(), upvoteInputData.getReview());
        userDataAccessObject.upvoteReview(upvoteInputData.getUser(), upvoteInputData.getReview());
        User upvotedUser = upvoteInputData.getUser();
        Review upvotedReview = upvoteInputData.getReview();
        if (upvotedUser.hasUpvoted(upvotedReview)) {
            upvotedUser.removeUpvote(upvotedReview);
            upvotedReview.removeUpvote();
        }
        else {
            upvotedUser.upvoteReview(upvotedReview);
            upvotedReview.addUpvote();
        }

        UpvoteOutputData upvoteOutputData = new UpvoteOutputData(upvotedUser, upvotedReview);
        upvotePresenter.prepareSuccessView(upvoteOutputData);
    }
}
