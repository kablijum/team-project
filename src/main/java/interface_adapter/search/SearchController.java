package interface_adapter.search;

import use_case.search.SearchInputData;
import use_case.search.SearchInputDataBoundary;

public class SearchController {
    private final SearchInputDataBoundary interactor;

    public SearchController(SearchInputDataBoundary interactor) {
        this.interactor = interactor;
    }

    public void executeSearch(String query) {
        SearchInputData input = new SearchInputData(query);
        interactor.execute(input);
    }
}

