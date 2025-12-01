package interface_adapter.view_profile_reviews;

import entity.Review;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ProfileReviewsViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public static class ReviewRow {
        private final int songID;
        private final String songTitle;
        private final int rating;
        private final String comment;

        public ReviewRow(int songID, String songTitle, int rating, String comment) {
            this.songID = songID;
            this.songTitle = songTitle;
            this.rating = rating;
            this.comment = comment;
        }

        public int getRating() {
            return rating;
        }

        public String getComment() {
            return comment;
        }

        public int getSongID() {
            return songID;
        }

        public String getSongTitle() {
            return songTitle;
        }
    }

    private String username;
    private final List<ReviewRow> reviews = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ReviewRow> getReviews() {
        return new ArrayList<>(reviews);
    }

    public void setReviews(List<ReviewRow> reviews) {
        this.reviews.clear();
        if (reviews != null) {
            this.reviews.addAll(reviews);
        }
    }

    public void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, null, this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
