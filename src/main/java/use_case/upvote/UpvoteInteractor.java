package use_case.upvote;

import use_case.login.LoginUserDataAccessInterface;

public class UpvoteInteractor implements UpvoteInputBoundary {
    private final UpvoteDataAccessInterface upvoteDataAccessObject;

    public UpvoteInteractor(UpvoteDataAccessInterface upvoteDataAccessObject) {
        this.upvoteDataAccessObject = upvoteDataAccessObject;
    }
    @Override
    public void execute(UpvoteInputData upvoteInputData) {
        upvoteDataAccessObject.upvoteReview(upvoteInputData.getUserName(), upvoteInputData.getReview());
    }
}
