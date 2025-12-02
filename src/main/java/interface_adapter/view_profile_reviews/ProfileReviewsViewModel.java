package interface_adapter.view_profile_reviews;

import entity.Review;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ProfileReviewsViewModel {
    /** Supports binding for view updates. */
    private final PropertyChangeSupport support
            = new PropertyChangeSupport(this);

    public static class ReviewRow {
        /** The ID of the song reviewed. */
        private final int songID;
        /** The title of the song reviewed. */
        private final String songTitle;
        /** The rating given to the song. */
        private final int rating;
        /** The comment provided in the review. */
        private final String comment;

        public ReviewRow(final int songID, final String songTitle,
                         final int rating, final String comment) {
            this.songID = songID;
            this.songTitle = songTitle;
            this.rating = rating;
            this.comment = comment;
        }
        /** @return the rating */
        public int getRating() {
            return rating;
        }
        /** @return the comment */
        public String getComment() {
            return comment;
        }
        /** @return the song ID */
        public int getSongID() {
            return songID;
        }
        /** @return the song title */
        public String getSongTitle() {
            return songTitle;
        }
    }

    private String username;
    /** A list of reviews associated with the user. */
    private final List<ReviewRow> reviews = new ArrayList<>();

    /** @return the current username */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username.
     * @param username the new username
     */
    public void setUsername(final String username) {
        this.username = username;
    }
    /** @return a copy of the list of reviews */
    public List<ReviewRow> getReviews() {
        return new ArrayList<>(reviews);
    }
    /**
     * Sets the list of reviews.
     * @param reviews the new list of reviews
     */
    public void setReviews(final List<ReviewRow> reviews) {
        this.reviews.clear();
        if (reviews != null) {
            this.reviews.addAll(reviews);
        }
    }
    /**
     * Fires a property change event to notify listeners.
     * @param propertyName the name of the property that changed
     */
    public void firePropertyChange(final String propertyName) {
        support.firePropertyChange(propertyName, null, this);
    }
    /**
     * Adds a listener for property changes.
     * @param listener the listener to add
     */
    public void addPropertyChangeListener(
            final PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
