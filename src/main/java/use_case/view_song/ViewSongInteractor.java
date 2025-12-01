package use_case.view_song;

import entity.Song;
import entity.Review;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewSongInteractor implements ViewSongInputDataBoundary {
    private final ViewSongOutputDataBoundary presenter;
    private final ViewSongDataAccessInterface dataAccess;
    private final ViewSongNewDataAccessInterface newSongDataAccess;
    private static final int INFO_VARIABLES = 3;

    public ViewSongInteractor(ViewSongOutputDataBoundary presenter,
                              ViewSongDataAccessInterface dataAccess,
                              ViewSongNewDataAccessInterface newSongDataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
        this.newSongDataAccess = newSongDataAccess;
    }

    @Override
    public void execute(ViewSongInputData inputData){
        int songID = inputData.getSongid();

        if (dataAccess.songExists(songID)) {

            Song song =  dataAccess.getSongById(songID);

            ViewSongOutputData outputData = getViewSongOutputData(song, songID);

            presenter.prepareSuccessView(outputData);
        }
        else {
            List<String> songInfo = null;
            try {
                songInfo = newSongDataAccess.getInfo(songID);
            } catch (Exception e) {
                throw new RuntimeException("API request failed.");
            }

            String title = songInfo.get(0);
            String artist = songInfo.get(1);
            Song newSong = new Song(songID, title, artist);
            dataAccess.saveSong(newSong);

            ViewSongOutputData outputData = new ViewSongOutputData(title, artist, songID);
            outputData.setMessage("Be the first to leave a review!");

            presenter.prepareNewSongView(outputData);

        }
    }

    @NotNull
    private static ViewSongOutputData getViewSongOutputData(Song song, int songID) {
        double rating = song.getAverageRating();
        String name = song.getName();
        String artist = song.getArtist();
        List<Review> reviewList = song.getReviews();
        Map<String, List<Object>> reviews = new HashMap<>();

        for (Review review : reviewList) {
            List<Object> info = new ArrayList<>(INFO_VARIABLES);
            info.add(review.getComment());
            info.add(review.getRating());
            info.add(review.getUpvotes());
            reviews.put(review.getUsername(), info);
        }

        ViewSongOutputData outputData = new ViewSongOutputData(name, artist, songID);
        outputData.setReviews(reviews);
        outputData.setAverageRating(rating);
        return outputData;
    }

}

