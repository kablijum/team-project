package use_case.view_profile;

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

        List<ViewProfileReviewData> reviewDataList =
                userDAO.getUserReviews(username);

        ViewProfileOutputData output =
                new ViewProfileOutputData(username, reviewDataList);

        presenter.present(output);
    }
}
