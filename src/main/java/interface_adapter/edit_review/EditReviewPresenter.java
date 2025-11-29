package interface_adapter.edit_review;

import interface_adapter.post_review.PostState;
import use_case.edit_review.EditOutputData;
import use_case.edit_review.EditOutputDataBoundary;
import use_case.post_review.PostOutputData;

public class EditReviewPresenter implements EditOutputDataBoundary {

    final  EditReviewViewModel editReviewViewModel;

    public EditReviewPresenter(EditReviewViewModel editReviewViewModel) {
        this.editReviewViewModel = editReviewViewModel;
    }

    @Override
    public void prepareSuccessView(EditOutputData outputData) {
        EditReviewState editState = editReviewViewModel.getState();
        editState.setComment(outputData.getComment());
        editState.setRating(outputData.getAverageRating());
        editState.setUsername(outputData.getUsername());
        editState.setSongName(outputData.getSongName());
    }

    @Override
    public void prepareFailView(String errorMessage){
        EditReviewState editState = editReviewViewModel.getState();
        editState.setErrorMessage(errorMessage);

    }
}
