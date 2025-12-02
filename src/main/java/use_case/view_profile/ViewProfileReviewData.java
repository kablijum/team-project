package use_case.view_profile;

public class ViewProfileReviewData {
    /** The ID of the song reviewed. */
    private final int songID;
    /** The title of the song reviewed. */
    private final String songTitle;
    /** The rating given. */
    private final int rating;
    /** The comment/text of the review. */
    private final String comment;

    public ViewProfileReviewData(final int songID, final String songTitle,
                                 final int rating, final String comment) {
        this.songID = songID;
        this.songTitle = songTitle;
//        this.artist = artist;
        this.rating = rating;
        this.comment = comment;
    }
    /**
     * @return the song ID
     */
    public int getSongID() {
        return songID;
    }
    /**
     * @return the song title
     */
    public String getSongTitle() { return songTitle; }
    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }
    /**
     * @return the review comment
     */
    public String getComment() {
        return comment;
    }
}
