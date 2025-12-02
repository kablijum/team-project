package use_case.view_profile;
/**
 * Defines the output contract for the View Profile use case (the Presenter interface).
 */
public interface ViewProfileOutputBoundary {
    /**
     * Presents the output data to the user.
     * @param outputData the output data
     */
    void present(ViewProfileOutputData outputData);
}
