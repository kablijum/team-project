package interface_adapter.post_review;


import use_case.post_review.PostInputData;
import use_case.post_review.PostInputDataBoundary;

public class PostController {
    private final PostInputDataBoundary interactor;

    public PostController(PostInputDataBoundary interactor) {
        this.interactor = interactor;
    }
    public void post(String comment, int rating, String user, int songid) {
        PostInputData postInputData = new PostInputData(comment, rating, user, songid);
        interactor.execute(postInputData);

    }


}
