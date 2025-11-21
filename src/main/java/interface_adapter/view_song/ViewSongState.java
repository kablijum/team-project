package interface_adapter.view_song;

import java.beans.PropertyChangeListener;
import java.util.Map;

public class ViewSongState {
    private Map<String, Object> reviews;
    private int averageRating;
    private String songName;
    private String artist;

    public Map<String, Object> getReviews() {
        return reviews;
    }
    public void setReviews(Map<String, Object> reviews) {
        this.reviews = reviews;
    }
    public int getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }
    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
}
