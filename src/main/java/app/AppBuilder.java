package app;

import data_access.DBSongDataAccessObject;
import data_access.SongDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.Song;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.post_review.PostController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.upvote_review.UpvoteController;
import interface_adapter.view_song.ViewSongController;
import interface_adapter.view_song.ViewSongPresenter;
import interface_adapter.view_song.ViewSongViewModel;
import interface_adapter.view_profile_reviews.ProfileReviewsController;
import interface_adapter.view_profile_reviews.ProfileReviewsViewModel;
import use_case.post_review.PostInputData;
import use_case.post_review.PostInputDataBoundary;
import use_case.upvote.UpvoteInputBoundary;
import use_case.upvote.UpvoteInputData;
import view.UserProfileView;
import org.jetbrains.annotations.NotNull;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.search.SearchInputDataBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputDataBoundary;
import use_case.search.SearchUserDataAccessInterface;
import use_case.view_song.*;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final UserFactory userFactory = new UserFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // set which data access implementation to use, can be any
    // of the classes from the data_access package

    // DAO version using a shared external database
    final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private HomeView homeView;
    private LoginView loginView;
    private SearchViewModel searchViewModel;
    private SearchController searchController;
    private ViewSongViewModel viewSongViewModel;
    private PostController postController;
    private SongProfileView songProfileView;
    private ProfileReviewsViewModel profileReviewsViewModel;
    private UserProfileView userProfileView;
    private ProfileReviewsController profileReviewsController;
    private ViewSongController viewSongController;
    private UpvoteController upvoteController;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }


    public AppBuilder addHomeView() {
        loggedInViewModel = new LoggedInViewModel();

        if (searchViewModel == null) {
            searchViewModel = new SearchViewModel();
        }
        if (viewSongController == null || viewSongViewModel == null) {
            addViewSongProfile();
        }
        if (searchController == null) {
            SearchUserDataAccessInterface dataAccess =
                    new SongDataAccessObject("JVa5EiX5BxAKq8MFac6DpgbKFlhMSbskByL1I5KeRE0sU0shOufi5NL3cEtNXMYK");
            SearchOutputDataBoundary presenter = new SearchPresenter(searchViewModel);
            SearchInputDataBoundary interactor = new SearchInteractor(dataAccess, presenter);
            searchController = new SearchController(interactor);
        }

        homeView = new HomeView(
                loggedInViewModel,
                searchViewModel,
                searchController,
                viewSongController,
                viewSongViewModel,
                viewManagerModel
        );

        cardPanel.add(homeView, HomeView.VIEW_NAME);
        return this;
    }

    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }


    public AppBuilder addSearchUseCase() {
        searchViewModel = new SearchViewModel();

        SearchUserDataAccessInterface dataAccess =
                new SongDataAccessObject("JVa5EiX5BxAKq8MFac6DpgbKFlhMSbskByL1I5KeRE0sU0shOufi5NL3cEtNXMYK");
        SearchOutputDataBoundary presenter = new SearchPresenter(searchViewModel);
        SearchInputDataBoundary interactor = new SearchInteractor(dataAccess, presenter);

        searchController = new SearchController(interactor);
        return this;
    }

    public AppBuilder addViewSongProfile() {

        if (viewSongViewModel == null) viewSongViewModel = new ViewSongViewModel();

        if (postController == null) {
            final PostInputDataBoundary postInputDataBoundary = new PostInputDataBoundary() {
                @Override
                public void execute(PostInputData postInputData) { }
            };
            postController = new PostController(postInputDataBoundary);
        }

        if (upvoteController == null) {
            final UpvoteInputBoundary upvoteInputBoundary = new UpvoteInputBoundary() {
                @Override
                public void execute(UpvoteInputData upvoteInputData) { }
            };
            upvoteController = new UpvoteController(upvoteInputBoundary);
        }

        ViewSongOutputDataBoundary presenter =
                new ViewSongPresenter(viewSongViewModel, viewManagerModel);

        ViewSongDataAccessInterface databaseDAO = new DBSongDataAccessObject();

        String token = "JVa5EiX5BxAKq8MFac6DpgbKFlhMSbskByL1I5KeRE0sU0shOufi5NL3cEtNXMYK";
        ViewSongNewDataAccessInterface externalAPI = new SongDataAccessObject(token);

        ViewSongInputDataBoundary interactor =
                new ViewSongInteractor(presenter, databaseDAO, externalAPI);

        viewSongController = new ViewSongController(interactor);

        songProfileView = new SongProfileView(
                viewSongController,
                viewSongViewModel,
                postController,
                upvoteController,
                loginViewModel
        );
        cardPanel.add(songProfileView, viewSongViewModel.getViewName());

        return this;
    }

    public AppBuilder addUserProfileView() {
        profileReviewsViewModel = new ProfileReviewsViewModel();

        profileReviewsController = new ProfileReviewsController(viewManagerModel);

        userProfileView = new UserProfileView(profileReviewsViewModel, profileReviewsController);
        cardPanel.add(userProfileView, UserProfileView.VIEW_NAME);

        if (homeView != null) {
            homeView.setProfileController(profileReviewsController);
        }

        return this;
    }

    // public AppBuilder addChangePasswordUseCase() {
    //    final ChangePasswordOutputBoundary changePasswordOutputBoundary = new ChangePasswordPresenter(viewManagerModel,
    //            loggedInViewModel);
    //
    //    final ChangePasswordInputBoundary changePasswordInteractor =
    //           new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

    //    ChangePasswordController changePasswordController = new ChangePasswordController(changePasswordInteractor);
    //    homeView.setChangePasswordController(changePasswordController);
    //    return this;
    //}

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
     //public AppBuilder addLogoutUseCase() {
     //final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
     //           loggedInViewModel, loginViewModel);

     //   final LogoutInputBoundary logoutInteractor =
     //           new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

     //   final LogoutController logoutController = new LogoutController(logoutInteractor);
     //   homeView.setLogoutController(logoutController);
     //   return this;
    //}

    public JFrame build() {
        final JFrame application = new JFrame("User Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        application.setSize(800, 600);
        application.setLocationRelativeTo(null);

        application.setMinimumSize(new Dimension(800, 600));
        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }
}
