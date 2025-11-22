package use_case.view_song;

import use_case.post_review.PostOutputData;

public interface ViewSongOutputDataBoundary {

    void prepareSuccessView(ViewSongOutputData postOutputData);

    void prepareNewSongView(PostOutputData postOutputData);
}
