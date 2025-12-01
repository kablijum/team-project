package interface_adapter.edit_review;

import interface_adapter.ViewManagerModel;
import use_case.edit_review.EditOutputData;
import use_case.edit_review.EditOutputDataBoundary;
import javax.swing.JOptionPane;

public class EditReviewPresenter implements EditOutputDataBoundary {

    private final ViewManagerModel viewManagerModel;

    public EditReviewPresenter(ViewManagerModel viewManagerModel) {

        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EditOutputData outputData) {
        JOptionPane.showMessageDialog(null,
                "Review updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void prepareFailView(String errorMessage) {

        JOptionPane.showMessageDialog(null,
                errorMessage,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
