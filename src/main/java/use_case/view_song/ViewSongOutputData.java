package use_case.view_song;

import interface_adapter.view_song.ViewSongViewModel;

import java.util.Map;

public class ViewSongOutputData {

    private String message;

    private String songName;
    private String artist;
    private double averageRating;
    private int songId;
    private Map<String, Object> reviews;

    ViewSongOutputData(String songName, String artist, int songid) {
        this.songId = songid;
        this.songName = songName;
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }
    public String getArtist() {
        return artist;
    }
    public int getSongId() {
        return songId;
    }

    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating){
        this.averageRating = averageRating;
    }
    public void setReviews(Map<String, Object> reviews) {
        this.reviews = reviews;
    }
    public Map<String, Object> getReviews() {
        return reviews;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}