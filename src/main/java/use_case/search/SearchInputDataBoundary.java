package use_case.search;

/**
 * The input boundary for the Search use case.
 * <p>
 * This interface defines the method that the controller must call
 * to initiate the search process. Implementations of this interface
 * (typically {@code SearchInteractor}) handle the business logic
 * of processing the search query and producing output data for the
 * presenter.
 * </p>
 */
public interface SearchInputDataBoundary {
    /**
     * Executes the search use case using the provided input data.
     *
     * @param inputData the search query and related information supplied
     *                  by the controller; must not be null
     */
    void execute(SearchInputData inputData);

}
