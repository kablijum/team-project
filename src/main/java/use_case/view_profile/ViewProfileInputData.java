package use_case.view_profile;

/**
 * Data structure containing the input information required to view a profile.
 */
public class ViewProfileInputData {
    /** The username of the profile to be viewed. */
    private final String username;
    /**
     * Constructs a ViewProfileInputData object.
     */
    public ViewProfileInputData(final String username) {
        this.username = username;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username; }
}
