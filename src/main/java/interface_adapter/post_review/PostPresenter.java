package interface_adapter.post_review;

import use_case.post_review.PostOutputData;
import use_case.post_review.PostOutputDataBoundary;

public class PostPresenter implements PostOutputDataBoundary{
    private final PostViewModel postViewModel;

    public  PostPresenter(PostViewModel postViewModel) {
        this.postViewModel = postViewModel;
    }
    public void prepareSuccessView(PostOutputData outputData){
        postViewModel.setComment(outputData.getComment());
        postViewModel.setRating(outputData.getRating());
        postViewModel.setUsername(outputData.getUsername());
        postViewModel.setSongname(outputData.getSongname());

    }
    public void prepareFailureView(String errorMessage){
        postViewModel.setErrorMessage(errorMessage);

    }



}
