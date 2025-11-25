package use_case.view_song;

import use_case.post_review.PostOutputData;

public interface ViewSongOutputDataBoundary {

    void prepareSuccessView(ViewSongOutputData viewSongOutputData);

    void prepareNewSongView(ViewSongOutputData viewSongOutputData);
}
