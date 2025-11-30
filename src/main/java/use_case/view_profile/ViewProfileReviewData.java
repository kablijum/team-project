package use_case.view_profile;

public class ViewProfileReviewData {
    private final int songID;
    private final String songTitle;
//    private final String artist;
    private final int rating;
    private final String comment;

    public ViewProfileReviewData(int songID, String songTitle, int rating, String comment) {
        this.songID = songID;
        this.songTitle = songTitle;
//        this.artist = artist;
        this.rating = rating;
        this.comment = comment;
    }

    public int getSongID() {
        return songID;
    }

    public String getSongTitle() { return songTitle; }

//    public String getArtist() { return artist; }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}
