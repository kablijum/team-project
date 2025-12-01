package use_case.search;

import entity.Song;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code SearchInteractor} implements the search use case in the application.
 *
 * <p>
 * It coordinates the logic of searching for songs by delegating data retrieval to
 * {@link SearchUserDataAccessInterface} and forwarding the result to
 * {@link SearchOutputDataBoundary} for presentation.
 *
 * <p>
 * This interactor does not perform any UI operations; it only contains application-level
 * business rules, following the principles of Clean Architecture.
 */

public class SearchInteractor  implements SearchInputDataBoundary {
    private final SearchUserDataAccessInterface dataAccess;
    private final SearchOutputDataBoundary presenter;

    public SearchInteractor(SearchUserDataAccessInterface dataAccess,
                            SearchOutputDataBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(SearchInputData inputData) {
        try {
            List<Song> songs = dataAccess.search(inputData.getQuery());

            List<SearchOutputData.SongResult> results = songs.stream()
                    .map(song -> new SearchOutputData.SongResult(
                            song.getId(),
                            song.getName(),
                            song.getArtist()
                    ))
                    .collect(Collectors.toList());

            presenter.prepareSuccessView(new SearchOutputData(results));

        } catch (Exception e) {
            presenter.prepareFailView("Search failed: " + e.getMessage());
        }
    }
}
