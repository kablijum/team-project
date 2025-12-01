package interface_adapter.upvote_review;

import use_case.upvote.UpvoteInputBoundary;
import use_case.upvote.UpvoteInputData;

public class UpvoteController {
    private final UpvoteInputBoundary upvoteUseCaseInteractor;

    public UpvoteController(final UpvoteInputBoundary useCaseInteractor) {
        this.upvoteUseCaseInteractor = useCaseInteractor;
    }

    /**
     * Executes the Upvote Use Case.
     * @param username is the username of the user who upvoted the review.
     * @param reviewUsername is the user who wrote the review.
     * @param songId is the id of the song which the review is written about.
     */
    public void execute(final String username, final String reviewUsername,
                        final int songId) {
        final UpvoteInputData upvoteInputData = new UpvoteInputData(username,
                reviewUsername, songId);

        upvoteUseCaseInteractor.execute(upvoteInputData);
    }
}
