package use_case.upvote;

/**
 * Interactor class for the upvote use case.
 */
public class UpvoteInteractor implements UpvoteInputBoundary {
    private final UpvoteSongDataAccessInterface songDataAccessObject;
    private final UpvoteUserDataAccessInterface userDataAccessObject;
    private final UpvoteOutputDataBoundary upvotePresenter;

    public UpvoteInteractor(final UpvoteSongDataAccessInterface
                                    upvoteSongDataAccessObject,
                            final UpvoteUserDataAccessInterface
                                    upvoteUserDataAccessObject,
                            final UpvoteOutputDataBoundary upvotepresenter) {
        this.songDataAccessObject = upvoteSongDataAccessObject;
        this.userDataAccessObject = upvoteUserDataAccessObject;
        this.upvotePresenter = upvotepresenter;
    }
    @Override
    public final void execute(final UpvoteInputData upvoteInputData) {
        // DAOs do not modify the InputData so we need to update it here.
        String username = upvoteInputData.getUsername();
        String reviewUsername = upvoteInputData.getReviewUsername();
        int songId = upvoteInputData.getSongId();

        boolean isUpvoted = userDataAccessObject.isUpvoted(username,
                reviewUsername, songId);
        if (!isUpvoted) {
            songDataAccessObject.upvoteReview(reviewUsername, songId);
            userDataAccessObject.upvoteReview(username, reviewUsername, songId);
        } else {
            songDataAccessObject.downvoteReview(reviewUsername, songId);
            userDataAccessObject.downvoteReview(username,
                    reviewUsername, songId);
        }

        UpvoteOutputData upvoteOutputData = new UpvoteOutputData(username,
                reviewUsername, songId, isUpvoted);
        upvotePresenter.prepareSuccessView(upvoteOutputData);
    }
}
