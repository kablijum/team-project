package use_case.edit_review;

import entity.User;

public interface EditUserDataAccessInterface {

    User get(String username);

    void save(User user);

    void updateUser(User user);
}
