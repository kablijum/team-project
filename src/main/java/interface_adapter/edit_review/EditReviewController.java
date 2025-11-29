package interface_adapter.edit_review;

import use_case.edit_review.EditInputData;
import use_case.edit_review.EditInputDataBoundary;

public class EditReviewController {

    private final EditInputDataBoundary interactor;

    public EditReviewController(EditInputDataBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String comment, int rating, String user, int songId,  int reviewIndex) {

        EditInputData inputData = new EditInputData(comment, rating, user, songId,  reviewIndex);
        interactor.execute(inputData);

    }
}
