package use_case.upvote;

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
        songDataAccessObject.upvoteReview(upvoteInputData.getUser(), upvoteInputData.getReview());
        userDataAccessObject.upvoteReview(upvoteInputData.getUser(), upvoteInputData.getReview());

        UpvoteOutputData upvoteOutputData = new UpvoteOutputData(upvoteInputData.getUser(), upvoteInputData.getReview());
        upvotePresenter.prepareSuccessView(upvoteOutputData);
    }
}
