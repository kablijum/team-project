package use_case.login;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the LoginInteractor using the LoginPresenterTest
 * as a fake presenter.
 */
public class LoginInteractorTest {

    @Test
    void successTest() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        User user = new User("Raha", "password123");
        userRepository.save(user);

        LoginPresenterTest presenter = new LoginPresenterTest();
        LoginInteractor interactor = new LoginInteractor(userRepository, presenter);

        LoginInputData inputData = new LoginInputData("Raha", "password123");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isSuccessViewCalled());
        assertFalse(presenter.isFailViewCalled());

        LoginOutputData outputData = presenter.getOutputData();
        assertNotNull(outputData);
        assertEquals("Raha", outputData.getUsername());

        // DAO should also track current username
        assertEquals("Raha", userRepository.getCurrentUsername());
    }

    @Test
    void failTestUserDoesNotExist() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        LoginPresenterTest presenter = new LoginPresenterTest();
        LoginInteractor interactor = new LoginInteractor(userRepository, presenter);

        LoginInputData inputData = new LoginInputData("ghost", "whatever");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("ghost: Account does not exist.", presenter.getErrorMessage());
    }

    @Test
    void failTestIncorrectPassword() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        User user = new User("Raha", "correctPassword");
        userRepository.save(user);

        LoginPresenterTest presenter = new LoginPresenterTest();
        LoginInteractor interactor = new LoginInteractor(userRepository, presenter);

        LoginInputData inputData = new LoginInputData("Raha", "wrongPassword");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("Incorrect password for \"Raha\".", presenter.getErrorMessage());
    }
}
