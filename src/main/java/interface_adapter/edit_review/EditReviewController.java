package interface_adapter.edit_review;

import use_case.edit_review.EditInputData;
import use_case.edit_review.EditInputDataBoundry;

public class EditReviewController {

    private final EditInputDataBoundry interactor;

    public EditReviewController(EditInputDataBoundry interactor) {
        this.interactor = interactor;
    }

    public void execute(String comment, int rating, String user, int songId) {

        EditInputData inputData = new EditInputData(comment, rating, user, songId);
        interactor.execute(inputData);

    }
}
