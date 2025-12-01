package use_case.search;

import org.junit.jupiter.api.Test;
import entity.Song;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchInteractorTest {

    static class FakeDataAccess implements SearchUserDataAccessInterface {
        List<Song> returnSongs = new ArrayList<>();
        boolean throwError = false;

        @Override
        public List<Song> search(String query) {
            if (throwError) {
                throw new RuntimeException("DB error");
            }
            return returnSongs;
        }

        @Override
        public List<String> getInfo(int songID) {
            return List.of();
        }
    }

    static class FakePresenter implements SearchOutputDataBoundary {
        SearchOutputData successData;
        String failMessage;

        @Override
        public void prepareSuccessView(SearchOutputData outputData) {
            this.successData = outputData;
        }

        @Override
        public void prepareFailView(String error) {
            this.failMessage = error;
        }
    }

    @Test
    void testSearchSuccess() {
        // GIVEN
        FakeDataAccess fakeDataAccess = new FakeDataAccess();
        FakePresenter fakePresenter = new FakePresenter();

        fakeDataAccess.returnSongs.add(new Song(1, "Imagine", "John Lennon"));
        fakeDataAccess.returnSongs.add(new Song(2, "Believer", "Imagine Dragons"));

        SearchInteractor interactor = new SearchInteractor(fakeDataAccess, fakePresenter);

        SearchInputData input = new SearchInputData("Imagine");

        // WHEN
        interactor.execute(input);

        // THEN
        assertNotNull(fakePresenter.successData);
        assertNull(fakePresenter.failMessage);

        List<SearchOutputData.SongResult> results = fakePresenter.successData.getResults();

        assertEquals(2, results.size());
        assertEquals(1, results.get(0).getId());
        assertEquals("Imagine", results.get(0).getName());
        assertEquals("John Lennon", results.get(0).getArtist());

        assertEquals(2, results.get(1).getId());
        assertEquals("Believer", results.get(1).getName());
        assertEquals("Imagine Dragons", results.get(1).getArtist());
    }

    @Test
    void testSearchError() {
        // GIVEN
        FakeDataAccess fakeDataAccess = new FakeDataAccess();
        FakePresenter fakePresenter = new FakePresenter();

        fakeDataAccess.throwError = true;

        SearchInteractor interactor = new SearchInteractor(fakeDataAccess, fakePresenter);

        SearchInputData input = new SearchInputData("anything");

        // WHEN
        interactor.execute(input);

        // THEN
        assertNull(fakePresenter.successData);
        assertEquals("Search failed: DB error", fakePresenter.failMessage);
    }
}

