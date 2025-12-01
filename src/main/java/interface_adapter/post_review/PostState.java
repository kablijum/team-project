package interface_adapter.post_review;

public class PostState {
    private String comment = "";
    private String username = "";
    private double rating;
    private String errorMessage;

    public void setComment(String comment){
        this.comment = comment;
    }
    public void setRating(double rating){
        this.rating = rating;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getComment(){
        return comment;
    }
    public double getRating(){
        return rating;
    }
    public String getUsername(){
        return username;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
}
