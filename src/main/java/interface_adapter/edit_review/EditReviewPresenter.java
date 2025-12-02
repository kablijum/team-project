package interface_adapter.edit_review;

import use_case.edit_review.EditOutputData;
import use_case.edit_review.EditOutputDataBoundary;

public class EditReviewPresenter implements EditOutputDataBoundary {

    private final EditReviewViewModel editReviewViewModel;

    public EditReviewPresenter(EditReviewViewModel editReviewViewModel) {
        this.editReviewViewModel = editReviewViewModel;
    }

    @Override
    public void prepareSuccessView(EditOutputData outputData) {
        EditReviewState state = editReviewViewModel.getState();
        state.setComment(outputData.getComment());
        state.setRating(outputData.getAverageRating());
        state.setUsername(outputData.getUsername());
        state.setSongName(outputData.getSongName());
        state.setSuccessMessage("Review updated successfully!");

        editReviewViewModel.setState(state);

        editReviewViewModel.firePropertyChange("editSuccess");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        EditReviewState state = editReviewViewModel.getState();
        state.setErrorMessage(errorMessage);
        editReviewViewModel.setState(state);
        editReviewViewModel.firePropertyChange("editFail");
    }
}
