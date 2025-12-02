package use_case.edit_review;

import data_access.InMemorySongDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EditInteractorTest {

    @Test
    void successTest() {
        // Create test data
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create("Juma", "password");

        SongFactory songFactory = new SongFactory();
        Song song = songFactory.create(12345, "Test Song", "Test Artist");

        // Add original review
        Review originalReview = new Review("Juma", "Original comment", 12345, 3, 5);
        song.addReview(originalReview);
        user.addWrittenReview(originalReview);

        EditInputData inputData = new EditInputData("Updated comment", 5, "Juma", 12345, 0);

        EditUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        EditSongDataAccessInterface songRepository = new InMemorySongDataAccessObject();
        songRepository.saveSong(song);

        EditPresenterTest presenter = new EditPresenterTest();

        EditInteractor interactor = new EditInteractor(userRepository, songRepository, presenter);
        interactor.execute(inputData);

        assertTrue(presenter.isSuccessViewCalled());

        EditOutputData outputData = presenter.getOutputData();
        assertEquals("Updated comment", outputData.getComment());
        assertEquals(5, outputData.getAverageRating());
        assertEquals("Juma", outputData.getUsername());
        assertEquals(12345, outputData.getSongId());
        assertEquals("Test Song", outputData.getSongName());
    }

    @Test
    void successTestPreservesUpvotes() {
        // Create test data with upvotes
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create("Juma", "password");

        SongFactory songFactory = new SongFactory();
        Song song = songFactory.create(12345, "Test Song", "Test Artist");

        Review originalReview = new Review("Juma", "Original comment", 12345, 3, 100);
        song.addReview(originalReview);
        user.addWrittenReview(originalReview);

        EditUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        EditSongDataAccessInterface songRepository = new InMemorySongDataAccessObject();
        songRepository.saveSong(song);

        EditPresenterTest presenter = new EditPresenterTest();

        EditInputData inputData = new EditInputData("Updated comment", 4, "Juma", 12345, 0);
        EditInteractor interactor = new EditInteractor(userRepository, songRepository, presenter);
        interactor.execute(inputData);

        assertTrue(presenter.isSuccessViewCalled());

        // Verify upvotes were preserved
        Song updatedSong = songRepository.getSongById(12345);
        assertEquals(100, updatedSong.getReviews().get(0).getUpvotes());
    }

    @Test
    void failTestEmptyComment() {
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create("Juma", "password");

        SongFactory songFactory = new SongFactory();
        Song song = songFactory.create(12345, "Test Song", "Test Artist");

        Review originalReview = new Review("Juma", "Original comment", 12345, 3, 0);
        song.addReview(originalReview);
        user.addWrittenReview(originalReview);

        EditUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        EditSongDataAccessInterface songRepository = new InMemorySongDataAccessObject();
        songRepository.saveSong(song);

        EditPresenterTest presenter = new EditPresenterTest();

        EditInputData inputData = new EditInputData("", 5, "Juma", 12345, 0);
        EditInteractor interactor = new EditInteractor(userRepository, songRepository, presenter);
        interactor.execute(inputData);

        assertTrue(presenter.isFailViewCalled());
        assertEquals("Comment cannot be empty", presenter.getErrorMessage());
    }

    @Test
    void failTestNullComment() {
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create("Juma", "password");

        SongFactory songFactory = new SongFactory();
        Song song = songFactory.create(12345, "Test Song", "Test Artist");

        Review originalReview = new Review("Juma", "Original comment", 12345, 3, 0);
        song.addReview(originalReview);
        user.addWrittenReview(originalReview);

        EditUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        EditSongDataAccessInterface songRepository = new InMemorySongDataAccessObject();
        songRepository.saveSong(song);

        EditPresenterTest presenter = new EditPresenterTest();

        EditInputData inputData = new EditInputData(null, 5, "Juma", 12345, 0);
        EditInteractor interactor = new EditInteractor(userRepository, songRepository, presenter);
        interactor.execute(inputData);

        assertTrue(presenter.isFailViewCalled());
        assertEquals("Comment cannot be empty", presenter.getErrorMessage());
    }
}
