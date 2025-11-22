package use_case.view_song;

import entity.Song;
import entity.Review;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewSongInteractor implements ViewSongInputDataBoundary {
    private ViewSongOutputDataBoundary presenter;
    private ViewSongDataAccessInterface dataAccess;

    public ViewSongInteractor(ViewSongOutputDataBoundary presenter,  ViewSongDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(ViewSongInputData inputData) {
        int songID = inputData.getSongid();

        if(dataAccess.songExists(songID)) {

            Song song =  dataAccess.getSongById(songID);
            double rating = song.getAverageRating();
            String name = song.getName();
            String artist = song.getArtist();
            List<Review> review_list = song.getReviews();
            Map<String, Object> reviews = new HashMap<>();

            for (Review review : review_list) {
                List<Object> info = new ArrayList<>(2);
                info.add(review.getComment());
                info.add(review.getRating());
                reviews.put(review.getUsername(), info);
            }
            // Reviews = {"username": [comment, rating] }

            ViewSongOutputData outputData = new ViewSongOutputData(name, artist, songID);
            outputData.setReviews(reviews);
            outputData.setAverageRating(rating);
            presenter.prepareSuccessView(outputData);
        }

        else {


        }

    }


    }
}
