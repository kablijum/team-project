package interface_adapter.view_song;

import use_case.view_song.ViewSongInputData;
import use_case.view_song.ViewSongInputDataBoundary;
import interface_adapter.ViewManagerModel;
import view.HomeView;

public class ViewSongController {
    private final ViewSongInputDataBoundary viewSongInteractor;
    private final ViewManagerModel viewManagerModel;

    public ViewSongController(ViewSongInputDataBoundary viewSongInteractor,
                              ViewManagerModel viewManagerModel) {
        this.viewSongInteractor = viewSongInteractor;
        this.viewManagerModel = viewManagerModel;

    }
    public void execute(int songid){
        ViewSongInputData data = new ViewSongInputData(songid);
        viewSongInteractor.execute(data);
    }
    public void goBackToHome() {
        viewManagerModel.setActiveView(HomeView.VIEW_NAME);
    }

}
