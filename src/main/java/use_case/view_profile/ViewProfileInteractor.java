package use_case.view_profile;

import entity.Review;
import entity.Song;

import java.util.ArrayList;
import java.util.List;

public class ViewProfileInteractor implements ViewProfileInputBoundary {
    /** Data access object for user-related data (reviews). */
    private final ViewProfileUserDataAccessInterface userDAO;
    /** Data access object for song-related data. */
    private final ViewProfileSongDataAccessInterface songDAO;
    /** The presenter to handle the output. */
    private final ViewProfileOutputBoundary presenter;
    public ViewProfileInteractor(final ViewProfileUserDataAccessInterface
                                         userDAO,
                                 final ViewProfileSongDataAccessInterface
                                         songDAO,
                                 final ViewProfileOutputBoundary presenter) {
        this.userDAO = userDAO;
        this.songDAO = songDAO;
        this.presenter = presenter;
    }
    /**
     * Executes the use case by fetching user reviews and preparing output data.
     * @param input the input data
     */
    @Override
    public void execute(final ViewProfileInputData input) {
        String username = input.getUsername();

        List<Review> userReviews = userDAO.getUserReviews(username);

        List<ViewProfileReviewData> reviewDataList = new ArrayList<>();

        for (Review r : userReviews) {
            Song s = songDAO.getSongById(r.getSongID());

            reviewDataList.add(new ViewProfileReviewData(
                    r.getSongID(),
                    s.getName(),
                    r.getRating(),
                    r.getComment()
            ));
        }

        ViewProfileOutputData output =
                new ViewProfileOutputData(username, reviewDataList);

        presenter.present(output);
    }
}
