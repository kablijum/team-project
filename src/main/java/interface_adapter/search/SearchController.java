package interface_adapter.search;

import use_case.search.SearchInputData;
import use_case.search.SearchInputDataBoundary;

/**
 * Controller for the Search use case.
 * <p>
 * This class receives user input from the UI layer (e.g., a search bar)
 * and packages it into a {@link SearchInputData} object, which is then
 * passed to the interactor through the {@link SearchInputDataBoundary}.
 * </p>
 * <p>
 * The controller performs no business logic; it only acts as a translator
 * between the view and the use case layer.
 * </p>
 */
public class SearchController {

    /** The input boundary (interactor) that handles the search use case. */
    private final SearchInputDataBoundary interactor;

    /**
     * Constructs a {@code SearchController} with the given interactor.
     *
     * @param interactor the use case input boundary responsible for executing
     *                   the search logic; must not be null
     */
    public SearchController(SearchInputDataBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Initiates the search operation using the provided query text.
     * <p>
     * This method is typically called by UI components such as views
     * or controllers when the user triggers a search (e.g., pressing a
     * search button).
     * </p>
     *
     * @param query the text entered by the user; may be validated by the interactor
     */
    public void executeSearch(String query) {
        SearchInputData input = new SearchInputData(query);
        interactor.execute(input);
    }
}
