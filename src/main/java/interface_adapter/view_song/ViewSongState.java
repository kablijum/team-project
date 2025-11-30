package interface_adapter.view_song;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewSongState {
    private List<ReviewViewModelItem> reviews;
    private double averageRating;
    private String songName;
    private String artist;
    private String message; // "Be the first to leave a review!"
    private int songid;

    public List<ReviewViewModelItem> getReviews() {
        return reviews;
    }
    public void setReviews(Map<String, List<Object>> reviews) {
       List<ReviewViewModelItem> items = new ArrayList<>();
        for (Map.Entry<String, List<Object>> entry : reviews.entrySet()) {
            ReviewViewModelItem newReview = new ReviewViewModelItem();
            newReview.setUsername(entry.getKey());
            List<Object> review = entry.getValue();
            newReview.setComment(review.get(0).toString());
            newReview.setRating(Integer.parseInt(review.get(1).toString()));
            newReview.setUpvotes(Integer.parseInt(review.get(2).toString()));

            items.add(newReview);
        }
        this.reviews = items;
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
