package interface_adapter.post_review;

import interface_adapter.ViewModel;

public class PostViewModel extends ViewModel<PostState> {

    public PostViewModel() {
        super("post");
        setState(new PostState());
    }

}


