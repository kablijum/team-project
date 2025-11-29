package use_case.edit_review;

import entity.Review;
import entity.User;

import java.util.List;

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
        final String username = inputData.getUsername();
        final int songId = inputData.getSongId();
        final String comment = inputData.getComment();
        final int rating = inputData.getRating();
        final int reviewIndex = inputData.getReviewIndex();

        if(!songData.existsByUsername(username, songId)){
            presenter.prepareFailView("You have not left a review to edit.");
        }
        if (comment == null || comment.trim().isEmpty()) {
            presenter.prepareFailView("Comment cannot be empty");
            return;
        }
        try {
            User user = userData.get(username);
            List<Review> writtenReviews = user.getWrittenReviews();

            if (reviewIndex < 0 || reviewIndex >= writtenReviews.size()) {
                presenter.prepareFailView("Invalid review index");
                return;
            }

            Review oldReview = writtenReviews.get(reviewIndex);

            Review updatedReview = new Review(
                    username,
                    comment,
                    oldReview.getSongID(),
                    rating,
                    oldReview.getUpvotes()
            );

            writtenReviews.remove(reviewIndex);
            writtenReviews.add(reviewIndex, updatedReview);

            userData.save(user);

            EditOutputData outputData = new EditOutputData(
                    username,
                    oldReview.getSongID(),
                    comment,
                    rating,
                    "Review updated successfully"
            );
            presenter.prepareSuccessView(outputData);

        } catch (Exception e) {
            presenter.prepareFailView("Failed to update review: " + e.getMessage());
        }
    }

}
