package interface_adapter.view_song;

public class ReviewViewModelItem {
    private String username;
    private String comment;
    private String songID;
    private int upvotes;
    private int rating;

    public String getUsername() {
        return username;
    }
    public String getComment() {
        return comment;
    }
    public String getSongID() {
        return songID;
    }
    public int getRating() {
        return rating;
    }
    public int getUpvotes() {
        return upvotes;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setSongID(String songID) {
        this.songID = songID;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }
    public String toString() {
        return username + ": " + comment + " (" + rating + ")";

    }

}
