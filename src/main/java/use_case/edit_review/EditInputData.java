package use_case.edit_review;

public class EditInputData {

    private final String comment;
    private final int rating;
    private final String username;
    private final int songId;
    private final int reviewIndex;


    public EditInputData(String comment, int rating,  String username, int songId,  int reviewIndex) {
        this.comment = comment;
        this.rating = rating;
        this.username = username;
        this.songId = songId;
        this.reviewIndex = reviewIndex;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public String getUsername() {
        return username;
    }

    public int getSongId() {
        return songId;
    }

    public int getReviewIndex() {
        return reviewIndex;
    }

}
