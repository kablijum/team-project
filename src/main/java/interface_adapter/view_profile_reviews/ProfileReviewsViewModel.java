package interface_adapter.view_profile_reviews;

import java.util.ArrayList;
import java.util.List;

public class ProfileReviewsViewModel {

    public static class ReviewRow {
        private final String songTitle;
        private final int rating;
        private final String comment;
        private final int songId;

        public ReviewRow(String songTitle, int rating, String comment, int songId) {
            this.songTitle = songTitle;
            this.rating = rating;
            this.comment = comment;
            this.songId = songId;
        }

        public String getSongTitle() {
            return songTitle;
        }

        public int getRating() {
            return rating;
        }

        public String getComment() {
            return comment;
        }

        public int getSongId() {
            return songId;
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
}
