package use_case.view_profile;

public class ViewProfileReviewData {
    private final int songID;
    private final int rating;
    private final String comment;

    public ViewProfileReviewData(int songID, int rating, String comment) {
        this.songID = songID;
        this.rating = rating;
        this.comment = comment;
    }
    public int getSongID() {
        return songID;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
