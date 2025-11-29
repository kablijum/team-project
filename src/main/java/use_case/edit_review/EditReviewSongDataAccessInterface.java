package use_case.edit_review;


public interface EditReviewSongDataAccessInterface {

    boolean existsByUsername(String username, int songid);

}
