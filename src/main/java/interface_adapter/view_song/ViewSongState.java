package interface_adapter.view_song;

import java.util.List;
import java.util.Map;

public class ViewSongState {
    private Map<String, List<Object>> reviews;
    private double averageRating;
    private String songName;
    private String artist;
    private String message; // "Be the first to leave a review!"
    private int songid;

    public Map<String, List<Object>> getReviews() {
        return reviews;
    }
    public void setReviews(Map<String, List<Object>> reviews) {
        this.reviews = reviews;
    }
    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating) {
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
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getSongId() {
        return songid;
    }
    public void setSongId(int songid) {
        this.songid = songid;
    }

}
