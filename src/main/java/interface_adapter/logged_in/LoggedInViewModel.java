package interface_adapter.logged_in;

import interface_adapter.ViewModel;
import view.HomeView;

/**
 * The View Model for the Logged In View.
 */
public class LoggedInViewModel extends ViewModel<LoggedInState> {

    public LoggedInViewModel() {
        super(HomeView.VIEW_NAME);
        setState(new LoggedInState());
    }

}
