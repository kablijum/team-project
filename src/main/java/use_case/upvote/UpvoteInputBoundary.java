package use_case.upvote;

/**
 * Input Boundary for actions which are related to upvoting a review.
 */
public interface UpvoteInputBoundary {
    /**
     * Executes the upvote use case. After this executes,
     * @param upvoteInputData is the input data to execute the upvote use case.
     */
    void execute(UpvoteInputData upvoteInputData);
}
