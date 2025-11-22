package use_case.upvote;

/**
 * Input Boundary for actions which are related to upvoting a review.
 */
public interface UpvoteInputBoundary {
    /**
     * Executes the upvote use case. After this executes,
     *
     */
    void execute(UpvoteInputData upvoteInputData);
}
