package interface_adapter.search;

import use_case.search.SearchOutputData;
import use_case.search.SearchOutputDataBoundary;

/**
 * Presenter for the Search use case.
 * <p>
 * This class implements the output boundary and is responsible for
 * transforming the raw output data from the interactor into a format
 * suitable for the {@link SearchViewModel}. It updates the view model
 * so that the UI layer can react accordingly.
 * </p>
 * <p>
 * The presenter performs no business logic and does not access data
 * sources. Its primary responsibility is preparing the view state.
 * </p>
 */
public class SearchPresenter implements SearchOutputDataBoundary {

    /** The view model that holds UI-ready state for the search view. */
    private final SearchViewModel viewModel;

    /**
     * Constructs a {@code SearchPresenter} with the given view model.
     *
     * @param viewModel the view model that will be updated based on
     *                  search results; must not be null
     */
    public SearchPresenter(SearchViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the success state when the search operation completes normally.
     * <p>
     * This method updates the view model with the results from
     * {@link SearchOutputData} and clears any previous error messages.
     * </p>
     *
     * @param outputData the search results produced by the interactor;
     *                   must not be null
     */
    @Override
    public void prepareSuccessView(SearchOutputData outputData) {
        viewModel.setResults(outputData.getResults());
        viewModel.setErrorMessage(null);
    }

    /**
     * Prepares the failure state when the search operation cannot be completed.
     * <p>
     * This method clears any previous results and places the error message
     * into the view model so the UI can display it.
     * </p>
     *
     * @param errorMessage the error message describing the failure; must not be null
     */
    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.setResults(null);
        viewModel.setErrorMessage(errorMessage);
    }
}
