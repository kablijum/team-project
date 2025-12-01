package use_case.post_review;

import data_access.InMemorySongDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.Song;
import entity.SongFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostInteractorTest {

    @Test
    void successTest() {
        // data to be tested
        UserFactory userfactory =  new UserFactory();
        User user = userfactory.create("AveryT", "password");

        SongFactory songFactory =  new SongFactory();
        Song song = songFactory.create(12345, "i love music" , "AwesomeArtist");


        PostInputData inputData = new PostInputData("Love the rhythm!", 4, "AveryT", 12345);

        PostReviewUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);
        PostReviewSongDataAccessInterface songRepository = new InMemorySongDataAccessObject();
        songRepository.saveSong(song);

        TestPostPresenter presenter = new TestPostPresenter();

        PostInteractor interactor = new PostInteractor(userRepository, songRepository, presenter);
        interactor.execute(inputData);

        assertTrue(presenter.isSuccessViewCalled());

        PostOutputData data = presenter.getOutputData();
        assertEquals("Love the rhythm!", data.getComment());
        assertEquals("AveryT", data.getUsername());
        assertEquals(12345, data.getSongid());
        assertEquals(4,data.getAverageRating());

        // Test to see if average review updates
        User user2 = userfactory.create("B", "password");
        userRepository.save(user2);

        PostInputData inputData2 = new PostInputData("hated it", 1, "B", 12345);

        interactor.execute(inputData2);

        PostOutputData data2 = presenter.getOutputData();
        assertEquals(2.5, data2.getAverageRating());


    }

    @Test
    void failTestDuplicate () {
        PostInputData inputData = new PostInputData("Love the rhythm!", 4, "AveryT", 12345);

        PostReviewUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        UserFactory userfactory =  new UserFactory();
        User user = userfactory.create("AveryT", "password");
        userRepository.save(user);

        PostReviewSongDataAccessInterface songRepository = new InMemorySongDataAccessObject();
        SongFactory songFactory =  new SongFactory();
        Song song = songFactory.create(12345, "i love music" , "AwesomeArtist");
        songRepository.saveSong(song);

        TestPostPresenter presenter = new TestPostPresenter();

        PostInteractor interactor = new PostInteractor(userRepository, songRepository, presenter);

        interactor.execute(inputData);
        PostInputData inputData2 = new PostInputData("Weird song", 2, "AveryT", 12345);

        interactor.execute(inputData2);

        assertTrue(presenter.isFailViewCalled());
        assertEquals("You have already left a review here", presenter.getErrorMessage());
    }

}



