package data_access;

import entity.Review;
import entity.Song;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class takes in a song data from the database as a form of JSONObject and returns Song object and vice versa.
 * Song data structure: {"id": , "name": "", "artist": "", "rating": , "reviews": []}
 */

public class SongMapper {
    private final JSONObject songJSONObject;
    private final Song song;
    private static final String SONGID = "songid";
    private static final String NAME = "name";
    private static final String ARTIST = "artist";
    private static final String RATING = "rating";
    private static final String REVIEWS = "reviews";

    public SongMapper(JSONObject songJSONObject) {
        this.songJSONObject = songJSONObject;
        this.song = null;
    }

    public SongMapper(Song song) {
        this.songJSONObject = null;
        this.song = song;
    }

    public Song mapJSONtoSong() {
        assert songJSONObject != null;
        int songid = songJSONObject.getInt(SONGID);
        String name = songJSONObject.getString(NAME);
        String artist = songJSONObject.getString(ARTIST);
        int rating = songJSONObject.getInt(RATING);
        JSONArray reviews = songJSONObject.getJSONArray(REVIEWS);

        Song mappedSong = new Song(songid, name, artist);
        for (int i = 0; i < reviews.length(); i++) {
            mappedSong.addReview(new ReviewMapper(reviews.getJSONObject(i)).mapJSONtoReview());
        }
        mappedSong.setRating(rating);
        return mappedSong;
    }

    public JSONObject mapSongtoJSON() {
        assert song != null;
        JSONObject mappedJSON = new JSONObject();
        mappedJSON.put(SONGID, song.getId());
        mappedJSON.put(NAME, song.getName());
        mappedJSON.put(ARTIST, song.getArtist());
        mappedJSON.put(RATING, song.getRating());
        mappedJSON.put(REVIEWS, new JSONArray());

        for (Review review : song.getReviews()) {
            JSONObject songReview = new ReviewMapper(review).mapJReviewtoJSON();
            mappedJSON.getJSONArray(REVIEWS).put(songReview);
        }
        return mappedJSON;
    }
}
