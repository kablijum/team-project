package use_case.edit_review;

public interface EditOutputDataBoundary {

    void prepareSuccessView(EditOutputData outputData);

    void prepareFailView(String errorMessage);
}
