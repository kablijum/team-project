package use_case.upvote;

/**
 * Interactor class for the upvote use case.
 */
public class UpvoteInteractor implements UpvoteInputBoundary {
    private final UpvoteOutputDataBoundary upvotePresenter;
    private final UpvoteRepository upvoteRepository;

    public UpvoteInteractor(final UpvoteRepository repo,
                            final UpvoteOutputDataBoundary presenter) {
        this.upvoteRepository = repo;
        this.upvotePresenter = presenter;
    }

    @Override
    public final void execute(final UpvoteInputData inputData) {
        boolean nowUpvoted = upvoteRepository.toggleUpvote(
                inputData.getUsername(),
                inputData.getReviewUsername(),
                inputData.getSongId()
        );
        UpvoteOutputData out = new UpvoteOutputData(
                inputData.getUsername(),
                inputData.getReviewUsername(),
                inputData.getSongId(),
                nowUpvoted
        );
        upvotePresenter.prepareSuccessView(out);
    }
}
