package use_case.signup;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the SignupInteractor using SignupPresenterTest
 * as a fake presenter.
 */
public class SignupInteractorTest {

    @Test
    void successTest() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        SignupPresenterTest presenter = new SignupPresenterTest();

        SignupInteractor interactor =
                new SignupInteractor(userRepository, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("Raha", "password123", "password123");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessViewCalled());
        assertFalse(presenter.isFailViewCalled());

        SignupOutputData outputData = presenter.getOutputData();
        assertNotNull(outputData);
        assertEquals("Raha", outputData.getUsername());

        // The user should actually be saved
        assertTrue(userRepository.existsByName("Raha"));
        User saved = userRepository.get("Raha");
        assertEquals("password123", saved.getPassword());
    }

    @Test
    void failTestUserAlreadyExists() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        SignupPresenterTest presenter = new SignupPresenterTest();

        // Pre-save existing user
        userRepository.save(userFactory.create("Raha", "oldPass"));

        SignupInteractor interactor =
                new SignupInteractor(userRepository, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("Raha", "newPass", "newPass");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("User already exists.", presenter.getErrorMessage());
    }

    @Test
    void failTestPasswordsDontMatch() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        SignupPresenterTest presenter = new SignupPresenterTest();

        SignupInteractor interactor =
                new SignupInteractor(userRepository, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("Raha", "password123", "different");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("Passwords don't match.", presenter.getErrorMessage());
    }

    @Test
    void failTestEmptyPassword() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        SignupPresenterTest presenter = new SignupPresenterTest();

        SignupInteractor interactor =
                new SignupInteractor(userRepository, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("Raha", "", "");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("New password cannot be empty", presenter.getErrorMessage());
    }

    @Test
    void failTestEmptyUsername() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        SignupPresenterTest presenter = new SignupPresenterTest();

        SignupInteractor interactor =
                new SignupInteractor(userRepository, presenter, userFactory);

        SignupInputData inputData =
                new SignupInputData("", "password123", "password123");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("Username cannot be empty", presenter.getErrorMessage());
    }

    @Test
    void testSwitchToLoginViewCallsPresenter() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        SignupPresenterTest presenter = new SignupPresenterTest();

        SignupInteractor interactor =
                new SignupInteractor(userRepository, presenter, userFactory);

        // Act
        interactor.switchToLoginView();

        // Assert
        assertTrue(presenter.isSwitchToLoginViewCalled());
    }
}
