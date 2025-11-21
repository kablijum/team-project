package interface_adapter.view_song;

import interface_adapter.ViewManagerModel;
import use_case.view_song.ViewSongOutputData;

public class ViewSongPresenter {

    private ViewSongViewModel viewSongViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewSongPresenter(ViewSongViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewSongViewModel = viewModel;
    }
    public void prepareSuccessView(ViewSongOutputData viewSongOutputData){
        final ViewSongState songState = viewSongViewModel.getState();
        songState.setReviews(viewSongOutputData.getReviews);
        songState.setAverageRating(viewSongOutputData.getAverageRating());
        songState.setSongName(viewSongOutputData.getSongName());
        songState.setArtist(viewSongOutputData.getArtist());
        this.viewSongViewModel.firePropertyChange();
    }

}

