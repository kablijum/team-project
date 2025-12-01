package use_case.view_profile;

import data_access.InMemorySongDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ViewProfileInteractorTest {
    @Test
    void successTest() {
        // Create a Song (ID: 101, Name: "Viva La Vida")
        SongFactory songFactory = new SongFactory();
        Song song = new Song(101, "Viva La Vida", "Coldplay");

        // Create a User, and add Song 101  review
        UserFactory userFactory = new UserFactory();
        User user = userFactory.create("Guo", "123456");

        // Create Review (username, comment, songId, rating, upvotes)
        Review review = new Review("Guo", "Best song ever", 101, 5, 0);
        user.addWrittenReview(review);

        // Prepare DAO
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        InMemorySongDataAccessObject songRepository = new InMemorySongDataAccessObject();
        songRepository.saveSong(song);

        TestProfilePresenter presenter = new TestProfilePresenter();

        // Use Case
        ViewProfileInputData inputData = new ViewProfileInputData("Guo");
        ViewProfileInteractor interactor = new ViewProfileInteractor(userRepository, songRepository, presenter);

        interactor.execute(inputData);

        assertTrue(presenter.isSuccessViewCalled());

        ViewProfileOutputData output = presenter.getOutputData();
        assertEquals("Guo", output.getUsername());

        assertEquals(1, output.getReviews().size());

        ViewProfileReviewData reviewData = output.getReviews().get(0);

        assertEquals(101, reviewData.getSongID());
        assertEquals("Viva La Vida", reviewData.getSongTitle());
        assertEquals(5, reviewData.getRating());
        assertEquals("Best song ever", reviewData.getComment());
    }
}

