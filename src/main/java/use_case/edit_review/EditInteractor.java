package use_case.edit_review;

import entity.Review;

public class EditInteractor implements EditInputDataBoundry{

    private final EditReviewUserDataAccesssInterface userData;
    private final EditReviewSongDataAccessInterface songData;
    private final EditOutputDataBoundry editPresenter;

    public EditInteractor(EditReviewUserDataAccesssInterface userData, EditReviewSongDataAccessInterface songData, EditOutputDataBoundry editPresenter){
        this.userData = userData;
        this.songData = songData;
        this.editPresenter = editPresenter;
    }

    @Override
    public void execute(EditInputData inputData) {
        final String username = inputData.getUsername();
        final int songId = inputData.getSongid();
        final String comment = inputData.getComment();
        final int rating = inputData.getRating();

        if(!songData.existsByUsername(username, songId)){
            editPresenter.prepareFailView("You have not left a review to edit.");
        }
        else{
            Review existingReview = songData.getReview(username, songId);
            int upvotes = existingReview.getUpvotes();

            Review updatedReview = new Review(username, comment, songId, rating, upvotes);

            userData.updateReview(updatedReview, username);
            songData.updateReview(updatedReview, songId);

            int newAverage = songData.getAverageRating(songId);
            String songName = songData.getSongName(songId);

            EditOutputData data = new EditOutputData(comment, newAverage, username, songId, songName);
            editPresenter.prepareSuccessView(data);
        }
    }

}
