package use_case.edit;

import use_case.edit_review.EditOutputData;
import use_case.edit_review.EditOutputDataBoundary;

public class EditPresenterTest implements EditOutputDataBoundary {

    private boolean successViewCalled = false;
    private boolean failViewCalled = false;
    private EditOutputData outputData;
    private String errorMessage;

    @Override
    public void prepareSuccessView(EditOutputData editOutputData) {
        this.successViewCalled = true;
        this.outputData = editOutputData;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        this.failViewCalled = true;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccessViewCalled() {
        return successViewCalled;
    }

    public boolean isFailViewCalled() {
        return failViewCalled;
    }

    public EditOutputData getOutputData() {
        return outputData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
