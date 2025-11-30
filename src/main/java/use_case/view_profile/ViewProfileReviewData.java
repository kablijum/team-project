package use_case.view_profile;

public class ViewProfileReviewData {
    private final String songTitle;
    private final int rating;
    private final String comment;
    private final int songId;

    public ViewProfileReviewData(String songTitle, int rating, String comment, int songId) {
        this.songTitle = songTitle;
        this.rating = rating;
        this.comment = comment;
        this.songId = songId;
    }

    public String getSongTitle() { return songTitle; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public int getSongId() { return songId; }
}
