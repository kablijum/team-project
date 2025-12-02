package use_case.edit_review;


import entity.Song;

public interface EditSongDataAccessInterface {

    Song getSongById(int songid);

    void saveSong(Song song);

}
