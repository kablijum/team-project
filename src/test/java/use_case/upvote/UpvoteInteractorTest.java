package use_case.upvote;

import data_access.DBSongDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.Review;
import entity.Song;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.signup.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UpvoteInteractorTest {

    @Test
    void successTest() {
        // Set up the data to be tested on
        User upvotedUser = new User("user1234", "1234");
        UserFactory userFactory = new UserFactory();
        Review upvotedReview = new Review("Paul", "Good Song", 1, 5, 0);
        Song reviewedSong = new Song(1, "Strangers", "Ruby Haunt");
        reviewedSong.addReview(upvotedReview);
        reviewedSong.updateAverageRating();
        DBSongDataAccessObject songInDB = new DBSongDataAccessObject(reviewedSong);
        songInDB.saveSong(reviewedSong);
        DBUserDataAccessObject userInDB = new DBUserDataAccessObject(userFactory);
        userInDB.save(upvotedUser);

        // Check if the upvote methods are correct in DB
        userInDB.upvoteReview(upvotedUser, upvotedReview);
        songInDB.upvoteReview(upvotedUser, upvotedReview);
        Set<Review> upvotedReviews = new HashSet<>();
        upvotedReviews.add(upvotedReview);

        assertEquals(2, upvotedReview.getUpvotes());
        assertEquals(upvotedReviews, upvotedUser.getUpvotedReviews());
    }
}
