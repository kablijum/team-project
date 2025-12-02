package use_case.search;

/**
 * The output boundary for the Search use case.
 * <p>
 * This interface defines how the interactor communicates results back to
 * the presenter. Implementations (typically {@code SearchPresenter})
 * transform the raw output data into a format suitable for the UI layer.
 * </p>
 */
public interface SearchOutputDataBoundary {

    /**
     * Prepares the success view when the search operation completes normally.
     * <p>
     * This method is called by the interactor when matching results
     * are found and packaged into a {@link SearchOutputData} object.
     * The presenter should use this data to update the view model.
     * </p>
     *
     * @param outputData the output data containing the list of search results;
     *                   must not be null
     */
    void prepareSuccessView(SearchOutputData outputData);

    /**
     * Prepares the failure view when the search operation cannot be completed.
     * <p>
     * Common failure cases include invalid input, empty queries, or no matches.
     * The presenter should display the provided error message to the user.
     * </p>
     *
     * @param errorMessage the message describing the reason for failure;
     *                     must not be null
     */
    void prepareFailView(String errorMessage);
}
