package interface_adapter.edit_review;

public class EditReviewState {

    private String comment = "";
    private String username = "";
    private String songName = "";
    private double rating;
    private String successMessage;
    private String errorMessage;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setSuccessMessage(String successMessage) {   // <-- RIGHT HERE
        this.successMessage = successMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public String getSongName() {
        return songName;
    }

    public double getRating() {
        return rating;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

