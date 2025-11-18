package use_case.post_review;

import entity.Review;

public class PostInteractor implements PostInputDataBoundary{

    private final PostReviewUserDataAccessInterface userDataAccess;
    private final PostReviewSongDataAccessInterface songDataAccess;
    private final PostOutputDataBoundary postPresenter;


    public PostInteractor(PostReviewUserDataAccessInterface userDataAccess, PostReviewSongDataAccessInterface songDataAccess, PostOutputDataBoundary postPresenter) {
        this.userDataAccess = userDataAccess;
        this.songDataAccess = songDataAccess;
        this.postPresenter = postPresenter;
    }

    public void execute(PostInputData inputData) {
        final String username = inputData.getUsername();
        final int songid = inputData.getSongid();
        final String comment =  inputData.getComment();
        final int rating = inputData.getRating();
        final int upvotes = 0;

        if (songDataAccess.existsByUsername(username,songid)) {
            postPresenter.prepareFailView("You have already left a review here");
        }
        else {
            Review review = new Review(username, comment, songid, rating, upvotes);
            userDataAccess.addReview(review,username);
            songDataAccess.addReview(review,songid);

            int newAverage = songDataAccess.getAverageRating(songid);
            String songname = songDataAccess.getSongName(songid);

            PostOutputData data = new PostOutputData(comment, newAverage, username, songname, songid);
            postPresenter.prepareSuccessView(data);
        }

    }
}
