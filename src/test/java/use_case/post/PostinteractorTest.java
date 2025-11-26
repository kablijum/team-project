package use_case.post;

import data_access.DBSongDataAccessObject;
import data_access.DBUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.post_review.PostInputData;
import use_case.post_review.PostReviewSongDataAccessInterface;
import use_case.post_review.PostReviewUserDataAccessInterface;

public class PostinteractorTest {

    @Test
    void successTest(){
        // data to be tested
        PostInputData inputData = new PostInputData("Love the rhythm!", 4, "AveryT", 1);
        User User = new User("AveryT", "L@vely");
        UserFactory userFactory = new UserFactory();

        PostReviewUserDataAccessInterface userRepository = new DBUserDataAccessObject(userFactory);
        PostReviewSongDataAccessInterface songDataAccessInterface = new DBSongDataAccessObject();

        
    }


}
