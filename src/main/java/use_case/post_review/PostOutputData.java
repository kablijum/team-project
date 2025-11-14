package use_case.post_review;

public class PostOutputData {

    private final String comment;
    private final int rating;
    private final String username;
    private final int songid;
    private final String songname;

    public PostOutputData(String comment, int rating, String username, String song, int songid) {
        this.comment = comment;
        this.rating = rating;
        this.username = username;
        this.songid = songid;
        this.songname = song;

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
    public int getSongid() {
        return songid;
    }
    public String getSongname() {
        return songname;
    }

}
