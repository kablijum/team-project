package use_case.logout;

import data_access.InMemoryUserDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the LogoutInteractor using LogoutPresenterTest
 * as a fake presenter.
 */
public class LogoutInteractorTest {

    @Test
    void successTestLogsOutCurrentUser() {
        // Arrange
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        userRepository.setCurrentUsername("raha");

        LogoutPresenterTest presenter = new LogoutPresenterTest();
        LogoutInteractor interactor = new LogoutInteractor(userRepository, presenter);

        // Act
        interactor.execute();

        // Assert: presenter called
        assertTrue(presenter.isSuccessViewCalled());

        LogoutOutputData outputData = presenter.getOutputData();
        assertNotNull(outputData);
        assertEquals("raha", outputData.getUsername());

        // DAO should now have no current user
        assertNull(userRepository.getCurrentUsername());
    }

    @Test
    void successTestWhenNoUserLoggedIn() {
        // Arrange: no current user set (null)
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        LogoutPresenterTest presenter = new LogoutPresenterTest();
        LogoutInteractor interactor = new LogoutInteractor(userRepository, presenter);

        // Act
        interactor.execute();

        // Assert
        assertTrue(presenter.isSuccessViewCalled());

        LogoutOutputData outputData = presenter.getOutputData();
        // username will be null in this case
        assertNull(outputData.getUsername());
        assertNull(userRepository.getCurrentUsername());
    }
}
