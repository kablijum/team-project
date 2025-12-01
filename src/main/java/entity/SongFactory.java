package entity;

public class SongFactory {
    public Song create(int songID, String title, String artist) {
        return new Song(songID, title, artist);
    }

}
