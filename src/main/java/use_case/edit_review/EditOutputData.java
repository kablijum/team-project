package use_case.edit_review;

public class EditOutputData {

    private final String comment;
    private final double rating;
    private final String username;
    private final int songId;
    private final String songName;

    public EditOutputData(String comment, double rating, String username, int songId, String songName) {
        this.comment = comment;
        this.rating = rating;
        this.username = username;
        this.songId = songId;
        this.songName = songName;
    }

    public String getComment() {
        return comment;
    }

    public double getRating() {
        return rating;
    }

    public String getUsername() {
        return username;
    }

    public int getSongId() {
        return songId;
    }

    public String getSongName() {
        return songName;
    }
}
