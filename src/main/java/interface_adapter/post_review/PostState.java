package interface_adapter.post_review;

public class PostState {
    private String comment = "";
    private String username = "";
    private String songname = "";
    private int rating;
    private String errorMessage;

    public void setComment(String comment){
        this.comment = comment;
    }
    public void setRating(int rating){
        this.rating = rating;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setSongname(String songname){
        this.songname = songname;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getComment(){
        return comment;
    }
    public int getRating(){
        return rating;
    }
    public String getUsername(){
        return username;
    }
    public String getSongname(){
        return songname;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
}
