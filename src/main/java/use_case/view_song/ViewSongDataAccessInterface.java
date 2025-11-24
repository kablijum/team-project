package use_case.view_song;

import entity.Song;
import entity.Review;

public interface ViewSongDataAccessInterface {

    boolean songExists(int songID);

    Song getSongById(int songID);

    void saveSong(Song song);

}

