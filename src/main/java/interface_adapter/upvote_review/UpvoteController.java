package interface_adapter.upvote_review;

import entity.Review;
import entity.User;
import use_case.upvote.UpvoteInputBoundary;
import use_case.upvote.UpvoteInputData;

public class UpvoteController {
    private final UpvoteInputBoundary upvoteUseCaseInteractor;

    public UpvoteController(UpvoteInputBoundary upvoteUseCaseInteractor) {
        this.upvoteUseCaseInteractor = upvoteUseCaseInteractor;
    }

    /**
     * Executes the Upvote Use Case.
     * @param user the username of the user logging in
     * @param review the review that is being upvoted
     */
    public void execute(User user, Review review) {
        final UpvoteInputData upvoteInputData = new UpvoteInputData(user, review);

        upvoteUseCaseInteractor.execute(upvoteInputData);
    }
}
