package use_case.view_profile;

import entity.Review;
import java.util.ArrayList;
import java.util.List;

public class ViewProfileInteractor implements ViewProfileInputBoundary {
    private final ViewProfileUserDataAccessInterface userDAO;
    private final ViewProfileOutputBoundary presenter;

    public ViewProfileInteractor(ViewProfileUserDataAccessInterface userDAO,
                                 ViewProfileOutputBoundary presenter) {
        this.userDAO = userDAO;
        this.presenter = presenter;
    }

    @Override
    public void execute(ViewProfileInputData input) {
        String username = input.getUsername();

        List<Review> userReviews = userDAO.getUserReviews(username);

        List<ViewProfileReviewData> reviewDataList = new ArrayList<>();

        for (Review r : userReviews) {
            reviewDataList.add(new ViewProfileReviewData(
                    r.getSongTitle(),     // entity getter
                    r.getRating(),
                    r.getComment(),
                    r.getSongID()
            ));
        }

        ViewProfileOutputData output =
                new ViewProfileOutputData(username, reviewDataList);

        presenter.present(output);
    }
}
