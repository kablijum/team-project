package use_case.view_profile;

import entity.Song;

public interface ViewProfileSongDataAccessInterface {
    /**
     * Retrieves a song based on its ID.
     * @param SongID the ID of the song
     * @return the Song entity
     */
    Song getSongById(int SongID);
}
