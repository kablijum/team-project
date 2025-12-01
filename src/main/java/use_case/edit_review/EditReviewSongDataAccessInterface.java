package use_case.edit_review;


import entity.Song;

public interface EditReviewSongDataAccessInterface {

    boolean existsByUsername(String username, int songid);

    Song getSongById(int songid);

    void saveSong(Song song);

}
