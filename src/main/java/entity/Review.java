package entity;


public class Review {

    private final String username;
    private final String comment;
    private final int songid;
    private final int rating;
    private int upvotes;

    /**
     * Creates a review with author,comment, associated song, rating and upvote count.
     * @param username of the user writing the review
     * @param comment the comment they left
     * @param songid the id number for the song this review is about
     * @param rating the number rating
     * @param upvotes the amount of upvotes on this review
     */

    public Review(String username, String comment, int songid, int rating, int upvotes) {
        this.username = username;
        this.comment = comment;
        this.songid = songid;
        this.rating = rating;
        this.upvotes = upvotes;
    }

    public int getRating() {
        return rating;
    }

    public String getUsername() {
        return username;
    }
    public String getComment() {
        return comment;
    }
    public int getSongID() {
        return songid;
    }
    public int getUpvotes() {
        return upvotes;
    }
    public void addUpvote() {
        this.upvotes++;
    }
    public void removeUpvote() {
        this.upvotes--;
    }
}