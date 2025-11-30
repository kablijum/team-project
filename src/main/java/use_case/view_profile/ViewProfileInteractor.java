package use_case.view_profile;

import entity.Review;
import entity.Song;

import java.util.ArrayList;
import java.util.List;

public class ViewProfileInteractor implements ViewProfileInputBoundary {
    private final ViewProfileUserDataAccessInterface userDAO;
    private final ViewProfileSongDataAccessInterface songDAO;
    private final ViewProfileOutputBoundary presenter;

    public ViewProfileInteractor(ViewProfileUserDataAccessInterface userDAO,
                                 ViewProfileSongDataAccessInterface songDAO,
                                 ViewProfileOutputBoundary presenter) {
        this.userDAO = userDAO;
        this.songDAO = songDAO;
        this.presenter = presenter;
    }

    @Override
    public void execute(ViewProfileInputData input) {
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
