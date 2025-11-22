package interface_adapter.view_song;

import use_case.view_song.ViewSongInputData;
import use_case.view_song.ViewSongInputDataBoundary;

public class ViewSongController {
    private final ViewSongInputDataBoundary viewSongInteractor;

    public ViewSongController(ViewSongInputDataBoundary viewSongInteractor) {
        this.viewSongInteractor = viewSongInteractor;

    }
    public void execute(int songid){
        ViewSongInputData data = new ViewSongInputData(songid);
        viewSongInteractor.execute(data);
    }

}
