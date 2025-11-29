package use_case.edit_review;

import use_case.post_review.PostOutputData;

public interface EditOutputDataBoundary {

    void prepareSuccessView(EditOutputData outputData);

    void prepareFailView(String errorMessage);
}
