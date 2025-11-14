package use_case.post_review;


public interface PostOutputDataBoundary {

    void prepareSuccessView(PostOutputData postOutputData);

    void prepareFailView( String errorMessage);
}
