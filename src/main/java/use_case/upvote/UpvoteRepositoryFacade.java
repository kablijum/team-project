package use_case.upvote;

public class UpvoteRepositoryFacade implements UpvoteRepository {

    private final UpvoteSongDataAccessInterface songDB;
    private final UpvoteUserDataAccessInterface userDB;

    /** Construct the facade class for upvote use case.
     * @param sDB is the DAI for the song database.
     * @param uDB is the DAI for the user database.
    */
    public UpvoteRepositoryFacade(
            final UpvoteSongDataAccessInterface sDB,
            final UpvoteUserDataAccessInterface uDB) {
        this.songDB = sDB;
        this.userDB = uDB;
    }

    @Override
    public final boolean toggleUpvote(final String username,
                                final String reviewUsername,
                                final int songId) {
        boolean isAlreadyUpvoted =
                userDB.isUpvoted(username, reviewUsername, songId);

        if (!isAlreadyUpvoted) {
            songDB.upvoteReview(reviewUsername, songId);
            userDB.upvoteReview(username, reviewUsername, songId);
            return true;  // now upvoted
        } else {
            songDB.downvoteReview(reviewUsername, songId);
            userDB.downvoteReview(username, reviewUsername, songId);
            return false; // now downvoted
        }
    }
}
