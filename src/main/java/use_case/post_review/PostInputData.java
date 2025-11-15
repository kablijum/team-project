package use_case.post_review;

public class PostInputData {
    private final String comment;
    private final int rating;
    private final String username;
    private final int songid;

    public PostInputData(String comment, int rating, String username, int songid) {
        this.comment = comment;
        this.rating = rating;
        this.username = username;
        this.songid = songid;
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

}
