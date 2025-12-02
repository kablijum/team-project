package use_case.view_profile;

/**
 * Defines the input contract for the View Profile use case.
 */
public interface ViewProfileInputBoundary {
    /**
     * Executes the View Profile use case.
     * @param inputData the input data containing the username
     */
    void execute(ViewProfileInputData inputData);
}
