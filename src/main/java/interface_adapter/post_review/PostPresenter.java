package interface_adapter.post_review;

import use_case.post_review.PostOutputData;
import use_case.post_review.PostOutputDataBoundary;

public class PostPresenter implements PostOutputDataBoundary{
    private final PostViewModel postViewModel;

    public  PostPresenter(PostViewModel postViewModel) {
        this.postViewModel = postViewModel;
    }

    @Override
    public void prepareSuccessView(PostOutputData outputData){
        PostState postState = postViewModel.getState();
        postState.setComment(outputData.getComment());
        postState.setRating(outputData.getAverageRating());
        postState.setUsername(outputData.getUsername());
        postState.setSongname(outputData.getSongname());

    }
    @Override
    public void prepareFailView(String errorMessage){
        PostState postState = postViewModel.getState();
        postState.setErrorMessage(errorMessage);

    }



}
