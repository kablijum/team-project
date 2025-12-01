package use_case.search;

/**
 * Input data for the search use case.
 * <p>
 * This class is a simple data carrier (DTO) that encapsulates the
 * user's search query, which will be passed from the controller
 * to the {@code SearchInputDataBoundary} (Interactor).
 * </p>
 *
 * <p>
 * It ensures that the interactor receives search-related information
 * in a structured and immutable way.
 * </p>
 */
public class SearchInputData {
    /** The text query entered by the user. */
    private final String query;

    /**
     * Constructs a new {@code SearchInputData} object containing
     * the user's search query.
     *
     * @param query the search text provided by the user; cannot be null
     */
    public SearchInputData(String query) {
        this.query = query;
    }

    /**
     * Returns the search query provided by the user.
     *
     * @return the non-null search query string
     */
    public String getQuery() {
        return query;
    }
}
