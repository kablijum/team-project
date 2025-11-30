package use_case.view_profile;

import entity.Song;

public interface ViewProfileSongDataAccessInterface {
    Song getSongById(int id);
}
