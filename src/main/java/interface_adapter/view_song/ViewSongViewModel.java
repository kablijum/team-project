package interface_adapter.view_song;

import interface_adapter.ViewModel;

public class ViewSongViewModel extends ViewModel<ViewSongState> {

    public ViewSongViewModel() {
        super("View song");
        setState(new ViewSongState());

    }
}
