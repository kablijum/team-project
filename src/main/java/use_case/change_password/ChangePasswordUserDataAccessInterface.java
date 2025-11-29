package use_case.change_password;

import entity.User;

/**
 * The DAO interface for the Change Password Use Case.
 */
public interface ChangePasswordUserDataAccessInterface {
    /**
     * Check if a user exists in the system.
     * @param username the username to check
     * @return true if the username already exists
     */
    boolean existsByName(String username);

    /**
     * Get a user object from the system.
     * @param username the username of the desired user
     * @return the User object associated with the username
     */
    User get(String username);

    /**
     * Update an existing user's password in the system.
     * @param user the updated user object whose password should be saved
     */
    void changePassword(User user);
}
