package interface_adapter.upvote_review;

import interface_adapter.ViewModel;

public class UpvoteViewModel extends ViewModel<UpvoteState> {
    /**
     * Create a new UpvoteViewModel by setting the current state UpvoteState.
     */
    public UpvoteViewModel() {
        super("Upvote");
        setState(new UpvoteState());
    }
}
