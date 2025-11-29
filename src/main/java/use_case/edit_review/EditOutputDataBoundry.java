package use_case.edit_review;

public interface EditOutputDataBoundry {

    void prepareSuccessView(EditOutputData outputData);

    void prepareFailView(String errorMessage);
}
