package use_case.change_password;

import data_access.InMemoryUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ChangePasswordInteractor using ChangePasswordPresenterTest
 * as a fake presenter.
 */
public class ChangePasswordInteractorTest {

    @Test
    void successTestChangesPassword() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        ChangePasswordPresenterTest presenter = new ChangePasswordPresenterTest();

        // Pre-save a user with an old password
        User original = userFactory.create("raha", "oldPassword");
        userRepository.save(original);

        ChangePasswordInteractor interactor =
                new ChangePasswordInteractor(userRepository, presenter, userFactory);

        ChangePasswordInputData inputData =
                new ChangePasswordInputData("raha", "newPassword123");

        // Act
        interactor.execute(inputData);

        // Assert: presenter success was called
        assertTrue(presenter.isSuccessViewCalled());
        assertFalse(presenter.isFailViewCalled());

        ChangePasswordOutputData outputData = presenter.getOutputData();
        assertNotNull(outputData);
        assertEquals("raha", outputData.getUsername());

        // And the password is actually changed in the repository
        User updated = userRepository.get("raha");
        assertNotNull(updated);
        assertEquals("newPassword123", updated.getPassword());
    }

    @Test
    void failTestUsernameMissing() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        ChangePasswordPresenterTest presenter = new ChangePasswordPresenterTest();

        ChangePasswordInteractor interactor =
                new ChangePasswordInteractor(userRepository, presenter, userFactory);

        // username is empty, password is non-empty
        ChangePasswordInputData inputData =
                new ChangePasswordInputData("", "somePassword");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("Username is required", presenter.getErrorMessage());
    }

    @Test
    void failTestPasswordMissing() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        ChangePasswordPresenterTest presenter = new ChangePasswordPresenterTest();

        ChangePasswordInteractor interactor =
                new ChangePasswordInteractor(userRepository, presenter, userFactory);

        // username provided, password empty
        ChangePasswordInputData inputData =
                new ChangePasswordInputData("raha", "");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("New password cannot be empty", presenter.getErrorMessage());
    }

    @Test
    void failTestUserDoesNotExist() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        UserFactory userFactory = new UserFactory();
        ChangePasswordPresenterTest presenter = new ChangePasswordPresenterTest();

        ChangePasswordInteractor interactor =
                new ChangePasswordInteractor(userRepository, presenter, userFactory);

        // No user with this username in repo
        ChangePasswordInputData inputData =
                new ChangePasswordInputData("ghostUser", "newPassword");

        // Act
        interactor.execute(inputData);

        // Assert
        assertTrue(presenter.isFailViewCalled());
        assertFalse(presenter.isSuccessViewCalled());
        assertEquals("User does not exist: ghostUser", presenter.getErrorMessage());
    }
}
