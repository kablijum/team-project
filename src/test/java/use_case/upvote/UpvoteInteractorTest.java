package use_case.upvote;

import data_access.InMemorySongDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import interface_adapter.upvote_review.UpvotePresenter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpvoteInteractorTest {


    @Test
    void upvoteSuccessTest() {
        // data to be tested
        UserFactory userfactory =  new UserFactory();
        User user = userfactory.create("UpvoteTestUser", "upvotetest");

        User reviewUser = userfactory.create("aaron", "1111");

        SongFactory songFactory =  new SongFactory();
        Song song = songFactory.create(12345, "Music" , "Artist1");

        Review review = new Review("aaron", "Cool song", 12345, 1, 0);
        song.addReview(review);
        reviewUser.addWrittenReview(review);


        UpvoteInputData inputData = new UpvoteInputData("UpvoteTestUser", "aaron", 12345);

        InMemoryUserDataAccessObject upvoteUserData = new InMemoryUserDataAccessObject();
        upvoteUserData.save(user);
        upvoteUserData.save(reviewUser);
        InMemorySongDataAccessObject upvoteSongData = new InMemorySongDataAccessObject();
        upvoteSongData.saveSong(song);

        TestUpvotePresenter presenter = new TestUpvotePresenter();

        UpvoteRepository upvoteRepository =
                new UpvoteRepositoryFacade(upvoteSongData, upvoteUserData);
        UpvoteInteractor interactor = new UpvoteInteractor(upvoteRepository, presenter);
        interactor.execute(inputData);

        assertTrue(presenter.isSuccessViewCalled());

        UpvoteOutputData data = presenter.getOutputData();
        assertEquals("UpvoteTestUser", data.getUsername());
        assertEquals("aaron", data.getReviewUsername());
        assertEquals(12345, data.getSongId());
        assertTrue(data.isUpvoted());

        // Test to see if upvote count increments
        assertEquals(1, review.getUpvotes());
    }

    @Test
    void downvoteSuccessTest () {
        // data to be tested
        UserFactory userfactory =  new UserFactory();
        User user = userfactory.create("UpvoteTestUser", "upvotetest");

        User reviewUser = userfactory.create("aaron", "1111");

        SongFactory songFactory =  new SongFactory();
        Song song = songFactory.create(12345, "Music" , "Artist1");

        Review review = new Review("aaron", "Cool song", 12345, 1, 1);
        song.addReview(review);
        reviewUser.addWrittenReview(review);
        user.upvoteReview(review);

        UpvoteInputData inputData = new UpvoteInputData("UpvoteTestUser", "aaron", 12345);

        InMemoryUserDataAccessObject upvoteUserData = new InMemoryUserDataAccessObject();
        upvoteUserData.save(user);
        upvoteUserData.save(reviewUser);
        InMemorySongDataAccessObject upvoteSongData = new InMemorySongDataAccessObject();
        upvoteSongData.saveSong(song);

        TestUpvotePresenter presenter = new TestUpvotePresenter();

        UpvoteRepository upvoteRepository =
                new UpvoteRepositoryFacade(upvoteSongData, upvoteUserData);
        UpvoteInteractor interactor = new UpvoteInteractor(upvoteRepository, presenter);
        interactor.execute(inputData);

        assertTrue(presenter.isSuccessViewCalled());

        UpvoteOutputData data = presenter.getOutputData();
        assertEquals("UpvoteTestUser", data.getUsername());
        assertEquals("aaron", data.getReviewUsername());
        assertEquals(12345, data.getSongId());
        assertFalse(data.isUpvoted());

        // Test to see if upvote count increments
        assertEquals(0, review.getUpvotes());
    }

}
