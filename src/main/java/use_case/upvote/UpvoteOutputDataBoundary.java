package use_case.upvote;

public interface UpvoteOutputDataBoundary {
    /**
     * Prepares the success view for the Upvote Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(UpvoteOutputData outputData);
}
