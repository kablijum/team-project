package interface_adapter.edit_review;

import use_case.edit_review.EditOutputData;
import use_case.edit_review.EditOutputDataBoundary;

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
