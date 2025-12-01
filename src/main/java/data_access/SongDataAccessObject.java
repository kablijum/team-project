package data_access;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Song;
import use_case.search.SearchUserDataAccessInterface;
import use_case.view_song.ViewSongNewDataAccessInterface;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SongDataAccessObject implements SearchUserDataAccessInterface, ViewSongNewDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String RESPONSE = "response";
    private static final String HITS = "hits";
    private static final String RESULT = "result";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String PRIMARY_ARTIST = "primary_artist";
    private static final String NAME = "name";
    private static final String SONG = "song";

    private final String token;

    public SongDataAccessObject(final String t) {
        this.token = t;
    }

    @Override
    public final List<Song> search(final String query) throws Exception {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String endpoint = "https://api.genius.com/search?q=" + encodedQuery;

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty(AUTH_HEADER, BEARER + token);

        int status = conn.getResponseCode();
        if (status != SUCCESS_CODE) {
            throw new RuntimeException("API request failed. Status code: " + status);
        }

        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        JsonArray hits = json.getAsJsonObject(RESPONSE).getAsJsonArray(HITS);

        List<Song> results = new ArrayList<>();

        for (var hit : hits) {
            JsonObject result = hit.getAsJsonObject().getAsJsonObject(RESULT);

            int id = result.get(ID).getAsInt();
            String title = result.get(TITLE).getAsString();
            String artist = result.getAsJsonObject(PRIMARY_ARTIST).get(NAME).getAsString();

            results.add(new Song(id, title, artist));
        }

        return results;
    }

    @Override
    public final List<String> getInfo(final int songID) throws Exception {
        URL url = new URL("https://api.genius.com/songs/" + songID);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty(AUTH_HEADER, BEARER + token);

        int status = conn.getResponseCode();
        if (status != SUCCESS_CODE) {
            throw new RuntimeException("API request failed. Status code: " + status);
        }

        InputStreamReader reader = new InputStreamReader(conn.getInputStream());
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

        JsonObject song = json.getAsJsonObject(RESPONSE).getAsJsonObject(SONG);

        List<String> info = new ArrayList<>(2);
        info.add(song.get(TITLE).getAsString());
        info.add(song.get(PRIMARY_ARTIST).getAsJsonObject().get(NAME).getAsString());

        return info;
    }
}
