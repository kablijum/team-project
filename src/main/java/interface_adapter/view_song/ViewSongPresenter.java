package interface_adapter.view_song;

import interface_adapter.ViewManagerModel;
import use_case.view_song.ViewSongOutputData;
import use_case.view_song.ViewSongOutputDataBoundary;

public class ViewSongPresenter implements ViewSongOutputDataBoundary {

    private ViewSongViewModel viewSongViewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewSongPresenter(ViewSongViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewSongViewModel = viewModel;
    }
    @Override
    public void prepareSuccessView(ViewSongOutputData viewSongOutputData){
        final ViewSongState songState = viewSongViewModel.getState();
        songState.setReviews(viewSongOutputData.getReviews());
        songState.setAverageRating(viewSongOutputData.getAverageRating());
        songState.setSongName(viewSongOutputData.getSongName());
        songState.setArtist(viewSongOutputData.getArtist());
        songState.setSongId(viewSongOutputData.getSongId());

        // Notify ViewModel listener
        this.viewSongViewModel.firePropertyChange();

        // Switch to viewSong view
        viewManagerModel.setState(viewSongViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareNewSongView(ViewSongOutputData viewSongOutputData){
        final ViewSongState songState = viewSongViewModel.getState();
        songState.setMessage(viewSongOutputData.getMessage());
        songState.setSongName(viewSongOutputData.getSongName());
        songState.setArtist(viewSongOutputData.getArtist());
        songState.setSongId(viewSongOutputData.getSongId());

        this.viewSongViewModel.firePropertyChange();

        viewManagerModel.setState(viewSongViewModel.getViewName());
        viewManagerModel.firePropertyChange();

    }


}

