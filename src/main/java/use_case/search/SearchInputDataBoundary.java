package use_case.search;

import use_case.view_song.ViewSongInputData;

public interface SearchInputDataBoundary {
    void execute(SearchInputData inputData);

    void execute(ViewSongInputData inputData);
}
