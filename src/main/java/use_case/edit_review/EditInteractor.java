package use_case.edit_review;

import entity.Review;
import entity.Song;
import entity.User;


public class EditInteractor implements EditInputDataBoundary {

    private final EditUserDataAccessInterface userData;
    private final EditReviewSongDataAccessInterface songData;
    private final EditOutputDataBoundary presenter;

    public EditInteractor(EditUserDataAccessInterface userData, EditReviewSongDataAccessInterface songData,
                          EditOutputDataBoundary presenter){
        this.userData = userData;
        this.songData = songData;
        this.presenter = presenter;
    }

    @Override
    public void execute(EditInputData inputData) {
        String username = inputData.getUsername();
        int songId = inputData.getSongId();
        String newComment = inputData.getComment();
        int newRating = inputData.getRating();


        if (newComment == null || newComment.trim().isEmpty()) {
            presenter.prepareFailView("Comment cannot be empty");
            return;
        }
        try {
            User user = userData.get(username);
            Song song = songData.getSongById(songId);

            Review oldReviewInSong = null;
            for (Review review : song.getReviews()) {
                if (review.getUsername().equals(username)) {
                    oldReviewInSong = review;
                    break;
                }
            }

            Review oldReviewInUser = null;
            for (Review review : user.getWrittenReviews()) {
                if (review.getSongID() == songId) {
                    oldReviewInUser = review;
                    break;
                }
            }

            assert oldReviewInSong != null;
            int oldUpvotes = oldReviewInSong.getUpvotes();

            song.deleteReview(oldReviewInSong);
            user.getWrittenReviews().remove(oldReviewInUser);

            Review newReview = new Review(username, newComment, songId, newRating, oldUpvotes);

            song.addReview(newReview);
            user.getWrittenReviews().add(newReview);

            song.updateAverageRating();

            userData.updateUser(user);
            songData.saveSong(song);

            EditOutputData outputData = new EditOutputData(newComment, newRating, username, songId, song.getName());
            presenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            presenter.prepareFailView("Failed to update review: " + e.getMessage());
        }
    }

}
