package use_case.search;

import java.util.List;

/**
 * Output data for the Search use case.
 * <p>
 * This class acts as a Data Transfer Object (DTO) that carries the results
 * of a search operation from the interactor to the presenter. It contains
 * a list of {@link SongResult} objects, each representing a matched song.
 * </p>
 */
public class SearchOutputData {

    /** A list of songs returned by the search query. */
    private final List<SongResult> results;

    /**
     * Constructs a {@code SearchOutputData} object containing the search results.
     *
     * @param results the list of matching songs produced by the interactor;
     *                must not be null
     */
    public SearchOutputData(List<SongResult> results) {
        this.results = results;
    }

    /**
     * Returns the list of search results.
     *
     * @return a list of {@link SongResult} objects
     */
    public List<SongResult> getResults() {
        return results;
    }

    /**
     * A single result entry representing a song matched by the search query.
     * <p>
     * This nested class encapsulates basic metadata about a song so the
     * presenter and UI can display it without exposing the full entity.
     * </p>
     */
    public static class SongResult {

        /** The unique identifier of the song. */
        private final int id;

        /** The name/title of the song. */
        private final String name;

        /** The artist who performed the song. */
        private final String artist;

        /**
         * Constructs a new {@code SongResult} representing a single search result.
         *
         * @param id     the ID of the song
         * @param name   the name/title of the song
         * @param artist the artist of the song
         */
        public SongResult(int id, String name, String artist) {
            this.id = id;
            this.name = name;
            this.artist = artist;
        }

        /**
         * Returns the ID of the song.
         *
         * @return the song ID
         */
        public int getId() {
            return id;
        }

        /**
         * Returns the name/title of the song.
         *
         * @return the song name
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the artist of the song.
         *
         * @return the artist name
         */
        public String getArtist() {
            return artist;
        }
    }
}

