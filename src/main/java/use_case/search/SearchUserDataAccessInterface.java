package use_case.search;

import entity.Song;
import java.util.List;

/**
 * The data access interface for the Search use case.
 * <p>
 * This interface defines how the interactor communicates with the
 * underlying data source (e.g., database, API, file storage) to
 * retrieve song information. Implementations of this interface should
 * not contain business logic; they only provide raw data access
 * operations.
 * </p>
 */
public interface SearchUserDataAccessInterface {

    /**
     * Searches for songs that match the given query.
     * <p>
     * This method is responsible for retrieving a list of {@link Song}
     * entities that match the user's search text. Different implementations
     * may use database queries, in-memory matching, or external APIs.
     * </p>
     *
     * @param query the search text provided by the user; must not be null
     * @return a list of matching songs, possibly empty but never null
     * @throws Exception if a data access error occurs (e.g., database error)
     */
    List<Song> search(String query) throws Exception;

    /**
     * Retrieves detailed information about a specific song.
     * <p>
     * This method enables the interactor or other use cases to fetch
     * additional metadata about a song using its unique identifier.
     * The returned list may include details such as description, genre,
     * release year, or other fields depending on the implementation.
     * </p>
     *
     * @param songID the ID of the song to retrieve information for
     * @return a list of metadata strings about the song
     * @throws Exception if the song cannot be found or a data access error occurs
     */
    List<String> getInfo(int songID) throws Exception;
}
