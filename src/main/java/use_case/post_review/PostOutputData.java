package use_case.post_review;

public class PostOutputData {

    private final String comment;
    private final double averageRating;
    private final String username;
    private final int songid;

    public PostOutputData(String comment, double rating, String username, int songid) {
        this.comment = comment;
        this.averageRating = rating;
        this.username = username;
        this.songid = songid;

    }

    public String getComment() {
        return comment;
    }
    public double getAverageRating() {
        return averageRating;
    }
    public String getUsername() {
        return username;
    }
    public int getSongid() {
        return songid;
    }

}
