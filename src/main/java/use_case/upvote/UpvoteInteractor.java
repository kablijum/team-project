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
        String username = upvoteInputData.getUsername();
        String reviewUsername = upvoteInputData.getReviewUsername();
        int songId = upvoteInputData.getSongId();

        boolean isUpvoted = userDataAccessObject.isUpvoted(username, reviewUsername, songId);
        if (!isUpvoted) {
            songDataAccessObject.upvoteReview(reviewUsername, songId);
            userDataAccessObject.upvoteReview(username, reviewUsername, songId);
        }
        else {
            songDataAccessObject.downvoteReview(reviewUsername, songId);
            userDataAccessObject.downvoteReview(username, reviewUsername, songId);
        }

        UpvoteOutputData upvoteOutputData = new UpvoteOutputData(username, reviewUsername, songId, isUpvoted);
        upvotePresenter.prepareSuccessView(upvoteOutputData);
    }
}
