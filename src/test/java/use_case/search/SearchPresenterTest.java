package use_case.search;


import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchPresenterTest {

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        SearchViewModel viewModel = new SearchViewModel();
        SearchPresenter presenter = new SearchPresenter(viewModel);

        List<SearchOutputData.SongResult> fakeResults = Arrays.asList(
                new SearchOutputData.SongResult(1, "Song A", "Artist A"),
                new SearchOutputData.SongResult(2, "Song B", "Artist B")
        );

        SearchOutputData outputData = new SearchOutputData(fakeResults);

        // Act
        presenter.prepareSuccessView(outputData);

        // Assert
        assertEquals(fakeResults, viewModel.getResults());
        assertNull(viewModel.getErrorMessage());
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        SearchViewModel viewModel = new SearchViewModel();
        SearchPresenter presenter = new SearchPresenter(viewModel);

        String error = "Song not found";

        // Act
        presenter.prepareFailView(error);

        // Assert
        assertNull(viewModel.getResults());
        assertEquals(error, viewModel.getErrorMessage());
    }
}
