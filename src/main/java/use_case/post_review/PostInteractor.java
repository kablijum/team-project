package use_case.post_review;

import entity.Review;
import entity.Song;

public class PostInteractor implements PostInputDataBoundary {
    private final PostReviewUserDataAccessInterface userDataAccess;
    private final PostReviewSongDataAccessInterface songDataAccess;
    private final PostOutputDataBoundary postPresenter;


    public PostInteractor(final PostReviewUserDataAccessInterface
                                  userDataAccess,
                          final PostReviewSongDataAccessInterface
                                  songDataAccess,
                          final PostOutputDataBoundary postPresenter) {
        this.userDataAccess = userDataAccess;
        this.songDataAccess = songDataAccess;
        this.postPresenter = postPresenter;
    }
    @Override
    public void execute(final PostInputData inputData) {
        final String username = inputData.getUsername();
        final int songid = inputData.getSongid();
        final String comment =  inputData.getComment();
        final int rating = inputData.getRating();
        final int upvotes = 0;
        if (songDataAccess.existsByUsername(username, songid)) {
            postPresenter.prepareFailView(
                    "You have already left a review here");

        } else {
            Review review = new Review(
                    username, comment, songid, rating, upvotes);
            userDataAccess.addReview(review, username);
            songDataAccess.addReview(review, songid);

            Song song = songDataAccess.getSongById(songid);

            double newAverage = song.getAverageRating();

            PostOutputData data = new PostOutputData(
                    comment, newAverage, username, songid);
            postPresenter.prepareSuccessView(data);
        }

    }
}
