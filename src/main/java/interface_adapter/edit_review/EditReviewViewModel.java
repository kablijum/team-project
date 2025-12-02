package interface_adapter.edit_review;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditReviewViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private EditReviewState state = new EditReviewState();

    public EditReviewState getState() {
        return state;
    }

    public void setState(EditReviewState state) {
        this.state = state;
    }

    public String getSuccessMessage() {
        return state.getSuccessMessage();
    }

    public void setSuccessMessage(String successMessage) {
        state.setSuccessMessage(successMessage);
    }

    public String getErrorMessage() {
        return state.getErrorMessage();
    }

    public void setErrorMessage(String errorMessage) {
        state.setErrorMessage(errorMessage);
    }

    public void firePropertyChange(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
