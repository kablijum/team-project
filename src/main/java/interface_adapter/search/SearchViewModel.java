package interface_adapter.search;

import use_case.search.SearchOutputData;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * ViewModel for the Search feature.
 * <p>
 * This class stores UI-ready state for the search view, including
 * the list of search results and potential error messages. It follows the
 * MVVM pattern commonly used in Clean Architecture interface adapters.
 * </p>
 *
 * <p>
 * The {@code SearchViewModel} notifies listeners (typically UI components)
 * of state changes through {@link PropertyChangeSupport}, enabling the
 * view to update automatically when the presenter modifies the state.
 * </p>
 */
public class SearchViewModel {

    /** Property name used when the search results list changes. */
    public static final String RESULTS_PROPERTY = "results";

    /** Property name used when the error message changes. */
    public static final String ERROR_PROPERTY = "error";

    /** Supports property change notifications for the view. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /** The list of search results to be displayed by the UI. */
    private List<SearchOutputData.SongResult> results;

    /** The error message to be shown when the search fails. */
    private String errorMessage;

    // --- setters ---

    /**
     * Sets the search results and notifies listeners of the change.
     *
     * @param results the new list of search results
     */
    public void setResults(List<SearchOutputData.SongResult> results) {
        List<SearchOutputData.SongResult> old = this.results;
        this.results = results;
        support.firePropertyChange(RESULTS_PROPERTY, old, results);
    }

    /**
     * Sets the error message and notifies listeners of the change.
     *
     * @param errorMessage the new error message to display, or {@code null} to clear errors
     */
    public void setErrorMessage(String errorMessage) {
        String old = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange(ERROR_PROPERTY, old, errorMessage);
    }

    // --- getters ---

    /**
     * Returns the current list of search results.
     *
     * @return the list of results, or {@code null} if none
     */
    public List<SearchOutputData.SongResult> getResults() {
        return results;
    }

    /**
     * Returns the current error message.
     *
     * @return the error message, or {@code null} if no error is present
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    // --- listener registration ---

    /**
     * Registers a listener to receive property change events.
     * <p>
     * Listeners are typically UI components that need to react when
     * the presenter updates the view model.
     * </p>
     *
     * @param listener the listener to register
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

